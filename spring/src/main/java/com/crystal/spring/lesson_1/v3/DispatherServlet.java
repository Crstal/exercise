package com.crystal.spring.lesson_1.v3;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DispatherServlet extends HttpServlet {

    // 用于保存配置文件中的参数
    private Properties contextConfig = new Properties();

    // 保存扫描到的类
    private List<String> classNames = new ArrayList<>();

    // ioc 容器
    private Map<String, Object> ioc = new HashMap<>();

    // handlerMapping
    private List<Handler> handlerMapping = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {

        // 1.加载配置文件
        loadContextConfig(config.getInitParameter("contextConfigLocation"));

        // 2.扫描相关的包
        doScanner(contextConfig.getProperty("scanPackage"));

        // 3.初始化扫描到的类，并住到IOC中
        doInstance();

        // 4.依赖注入
        doAutowired();

        // 5.初始化HandlerMapping
        initHandlerMapping();
        System.out.println("Spring framework is inited!!");
    }

    private void initHandlerMapping() {
        if (ioc.size() <= 0) { return; }
        try {
            for (Object obj : ioc.values()) {
                Class<?> clazz = obj.getClass();
                if (clazz.isAnnotationPresent(Controller.class)) {
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
                                String regex = ("/" + baseUrl + "/" + methodRequestMapping.value() ).replaceAll("/+", "/");
                                Pattern pattern = Pattern.compile(regex);
                                Handler mapping = new Handler(pattern, obj, method);
                                handlerMapping.add(mapping);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doAutowired() {
        if (ioc.size() <= 0) { return; }
        try {
            for (Object obj : ioc.values()) {
                Class clazz = obj.getClass();
                if (clazz.isAnnotationPresent(Controller.class)) {
                    Field[] fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        if (field.isAnnotationPresent(Autowired.class)) {
                            Autowired autowired = field.getAnnotation(Autowired.class);
                            String beanName = autowired.value();
                            if ("".equals(beanName)) {
                                beanName = field.getType().getName();
                            }
                            field.setAccessible(true);
                            field.set(ioc.get(toFirstLowerCase(clazz.getSimpleName())), ioc.get(beanName));
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void doInstance() {
        if (classNames.size()==0) { return; }

        try {
            // 加载实例
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(Controller.class)) {
                    Object instance = clazz.newInstance();
                    //Spring 默认类名首字母小写
                    String beanName = toFirstLowerCase(clazz.getSimpleName());
                    ioc.put(beanName,instance);
                } else if (clazz.isAnnotationPresent(Service.class)) {
                    Service service = clazz.getAnnotation(Service.class);
                    String beanName = service.value();
                    if ("".equals(beanName)) {
                        beanName = toFirstLowerCase(clazz.getName());
                    }
                    Object instance = clazz.newInstance();
                    ioc.put(beanName, instance);

                    for (Class<?> i : clazz.getInterfaces()) {
                        ioc.put(i.getName(), instance);
                    }
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 读取配置文件
     * @param config
     */
    private void loadContextConfig(String config) {
        InputStream inputStream = null;
        try {
            inputStream = this.getClass().getClassLoader().getResourceAsStream(config);
            contextConfig.load(inputStream);
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
                    classNames.add(className);
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

        Handler handler = getHandler(req);
        if (handler == null) {
            resp.getWriter().write("404 NOT FOUND!!");
            return;
        }

        Method method = handler.getMethod();
        //获取方法的形参列表
        Class<?> [] paramTypes = method.getParameterTypes();
        Object [] paramValues = new Object[paramTypes.length];
        Map<String,String[]> params = req.getParameterMap();
        for (Map.Entry<String, String[]> parm : params.entrySet()) {
            String value = Arrays.toString(parm.getValue()).replaceAll("\\[|\\]","")
                    .replaceAll("\\s",",");
            if(!handler.getParamIndexMapping().containsKey(parm.getKey())){continue;}
            int index = handler.getParamIndexMapping().get(parm.getKey());
            paramValues[index] = convert(paramTypes[index],value);
        }

        if (handler.getParamIndexMapping().containsKey(HttpServletRequest.class.getName())) {
            int reqIndex = handler.getParamIndexMapping().get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = req;
        }
        if (handler.getParamIndexMapping().containsKey(HttpServletResponse.class.getName())) {
            int respIndex = handler.getParamIndexMapping().get(HttpServletResponse.class.getName());
            paramValues[respIndex] = resp;
        }

        Object returnValue = method.invoke(handler.getController(), paramValues);
        if(returnValue == null || returnValue instanceof Void){ return; }
        resp.getWriter().write(returnValue.toString());
    }

    private Handler getHandler(HttpServletRequest request) {
        if (handlerMapping.isEmpty()) { return null; }
        String url = request.getRequestURI();
        String contextPath = request.getContextPath();
        url = url.replace(contextPath, "").replace("/+", "/");
        for (Handler handler: handlerMapping) {
            Matcher matcher = handler.getPattern().matcher(url);
            if (!matcher.matches()) { continue; }
            return handler;
        }
        return null;
    }

    //url 传过来的参数都是 String 类型的， HTTP 是基于字符串协议
    //只需要把 String 转换为任意类型就好
    private Object convert(Class<?> type,String value){
        //如果是 int
        if(Integer.class == type){
            return Integer.valueOf(value);
        }
        //如果还有 double 或者其他类型， 继续加 if
        //这时候， 我们应该想到策略模式了
        //在这里暂时不实现， 希望小伙伴自己来实现
        return value;
    }
}
