package com.crystal.pattern.proxy.custom;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author: Caoyue
 * @Description: 用于生成代理类并编译
 * @Date: 2018/05/24 21:49
 */
public class GPProxy {
    public static final String ln = "\r\n"; // 换行

    public static Object newProxyInstance(GPClassLoader classLoader, Class<?>[] interfaces, GPInvocationHandler h) {
        FileWriter writer = null;
        try {
            // 1.生成源码
            String src = generateSrc(interfaces);

            // 2.写入文件
            File f = new File(GPProxy.class.getResource("").getPath() + "$Proxy0.java");
            writer = new FileWriter(f);
            writer.write(src);
            writer.flush();
            writer.close();

            // 3.编译成class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
            Iterable iterable = manager.getJavaFileObjects(f);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
            task.call();
            manager.close();

            //4、编译生成的.class文件加载到JVM中来
            Class proxyClass = classLoader.findClass("$Proxy0");
            Constructor c = proxyClass.getConstructor(GPInvocationHandler.class);
            f.delete();

            //5、返回字节码重组以后的新的代理对象
            Object obj = c.newInstance(h);
            return obj;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String generateSrc(Class<?>[] interfaces) {
        StringBuffer sb = new StringBuffer();
        sb.append("package com.crystal.pattern.proxy.custom;" + ln);
        sb.append("import " + interfaces[0].getName() + ";" + ln);
        sb.append("import java.lang.reflect.Method;" + ln);
        sb.append("public class $Proxy0 implements " + interfaces[0].getSimpleName() + "{" + ln);
        sb.append("GPInvocationHandler h;" + ln);
        sb.append("public $Proxy0(GPInvocationHandler h) { " + ln);
        sb.append("this.h = h;");
        sb.append("}" + ln);

        for (Method m : interfaces[0].getMethods()){
            sb.append("public " + m.getReturnType().getName() + " " + m.getName() + "() {" + ln);
            sb.append(m.getReturnType().getName() + " obj = null;" + ln);
            sb.append("try{" + ln);
            sb.append("Method m = " + interfaces[0].getSimpleName() + ".class.getMethod(\"" + m.getName() + "\",new Class[]{});" + ln);
            sb.append("obj = (" + m.getReturnType().getName() + ") this.h.invoke(this,m,null);" + ln);
            sb.append("}catch(Throwable e){" + ln);
            sb.append("e.printStackTrace();" + ln);
            sb.append("}" + ln);
            sb.append("return obj;" + ln);
            sb.append("}");
        }
        sb.append("}" + ln);
        return sb.toString();
    }
}
