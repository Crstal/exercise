package com.crystal.spring.lesson_1.v2;

import com.crystal.spring.lesson_1.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class DispatherServlet extends HttpServlet {

    // 用于保存配置文件中的参数
    private Properties contextConfig = new Properties();

    // 保存扫描到的类
    private List<String> classNames = new ArrayList<>();

    // ioc 容器
    private Map<String, Object> ioc = new HashMap<>();

    // handlerMapping
    private Map<String, Object> handlerMapping = new HashMap<>();

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
                                String url = (baseUrl + "/" + methodRequestMapping.value() ).replaceAll("/+", "/");
                                handlerMapping.put(url, method);
                                System.out.println("Mapped " + url + "," + method);
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
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replace("/+", "/");
        if (!handlerMapping.containsKey(url)) { resp.getWriter().write("404 NOT FOUND!!"); }

        Method method = (Method) handlerMapping.get(url);
        // 从 reqest 中拿到 url 传过来的参数
        Map<String,String[]> params = req.getParameterMap();
        //获取方法的形参列表
        Class<?> [] parameterTypes = method.getParameterTypes();
        Object [] paramValues = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i ++) {
            Class parameterType = parameterTypes[i];
            //不能用 instanceof， parameterType 它不是实参， 而是形参
            if (parameterType == HttpServletRequest.class) {
                paramValues[i] = req;
                continue;
            } else if (parameterType == HttpServletResponse.class) {
                paramValues[i] = resp;
                continue;
            } else if (parameterType == String.class) {
                Annotation[] annotations = method.getParameterAnnotations()[i];
                for (int j=0; j<annotations.length; j++) {
                    if (annotations[j] instanceof RequestParam) {
                        RequestParam requestParam = (RequestParam) annotations[j];
                        if (params.containsKey(requestParam.value())) {
                            for (Map.Entry<String, String[]> param : params.entrySet()) {
                                String value = Arrays.toString(param.getValue())
                                        .replaceAll("\\[|\\]", "")
                                        .replaceAll("\\s", ",");
                                paramValues[i] = value;
                                break;
                            }
                        }
                    }
                }

            }
        }

        // 投机取巧的方式
        //通过反射拿到 method 所在 class， 拿到 class 之后还是拿到 class 的名称
        //再调用 toLowerFirstCase 获得 beanName
        String beanName = toFirstLowerCase(method.getDeclaringClass().getSimpleName());
        method.invoke(ioc.get(beanName),paramValues);
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
