package com.crystal.spring.lesson_1.v1;

import com.crystal.spring.lesson_1.annotation.Autowired;
import com.crystal.spring.lesson_1.annotation.Controller;
import com.crystal.spring.lesson_1.annotation.RequestMapping;
import com.crystal.spring.lesson_1.annotation.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class DispatherServlet extends HttpServlet {

    private Map<String, Object> mapping = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        InputStream inputStream = null;
        Properties configContext = new Properties();
        // 读取配置文件
        try {
            inputStream = this.getClass().getClassLoader().getResourceAsStream(config.getInitParameter("contextConfigLocation"));
            configContext.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            // 扫描包
            String scanPackage = configContext.getProperty("scanPackage");
            doScanner(scanPackage);

            // 加载实例
            if (mapping != null && mapping.size()>0) {
                Map<String, Object> objectMap = new HashMap<>();
                Set<String> classNames = mapping.keySet();
                for (String className: classNames) {
                    Class<?> clazz = Class.forName(className);
                    if (clazz.isAnnotationPresent(Controller.class)) {
                        objectMap.put(className, clazz.newInstance());
                        String baseUrl = "";
                        if (clazz.isAnnotationPresent(RequestMapping.class)) {
                            RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
                            baseUrl += requestMapping.value();
                        }
                        Method[] methods = clazz.getMethods();
                        if (methods != null && methods.length>0) {
                            for (Method method: methods) {
                                if (method.isAnnotationPresent(RequestMapping.class)) {
                                    RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
                                    String url = (baseUrl + "/" + methodRequestMapping.value() ).replaceAll("/+", "/");
                                    objectMap.put(url, method);
                                    System.out.println("Mapped " + url + "," + method);
                                }
                            }
                        }
                    } else if (clazz.isAnnotationPresent(Service.class)) {
                        Service service = clazz.getAnnotation(Service.class);
                        String beanName = service.value();
                        if ("".equals(beanName)) { beanName = toFirstLowerCase(clazz.getName()); }
                        Object instance = clazz.newInstance();
                        objectMap.put(beanName, instance);

                        for (Class<?> i: clazz.getInterfaces()) {
                            objectMap.put(i.getName(), instance);
                        }
                    }
                }


                mapping.putAll(objectMap);
                for (Object obj : objectMap.values()) {
                    if (obj == null || obj instanceof Method) { continue; }
                    Class clazz = obj.getClass();
                    if(clazz.isAnnotationPresent(Controller.class)) {
                        Field[] fields = clazz.getDeclaredFields();
                        for (Field field : fields) {
                            if (field.isAnnotationPresent(Autowired.class)) {
                                Autowired autowired = field.getAnnotation(Autowired.class);
                                String beanName = autowired.value();
                                if ("".equals(beanName)) {
                                    beanName = field.getType().getName();
                                }
                                field.setAccessible(true);
                                field.set(mapping.get(clazz.getName()), mapping.get(beanName));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String toFirstLowerCase(String beanName) {
        char[] chars = beanName.toCharArray();
        chars[0] += 32;
        return new String(chars);
    }

    /**
     * 扫描包 将包下面所有的class类存入mapping
     * @param scanPackage
     */
    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classDir = new File(url.getFile());
        if (classDir != null && classDir.isDirectory()) {
            for (File file : classDir.listFiles()) {
                if (file.isDirectory()) {
                    doScanner(scanPackage + "." + file.getName());
                } else {
                    if (!file.getName().endsWith(".class")) { continue; }
                    String className = scanPackage + "." + file.getName().replace(".class", "");
                    mapping.put(className, null);
                }
            }
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Exception " + Arrays.asList(e.getStackTrace()));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replace("/+", "/");
        if (!mapping.containsKey(url)) { resp.getWriter().write("404 NOT FOUND!!"); }

        Method method = (Method) mapping.get(url);
        Map<String, String[]> params = req.getParameterMap();
        method.invoke(this.mapping.get(method.getDeclaringClass().getName()),new
                Object[]{req,resp,params.get("name")[0]});
    }
}
