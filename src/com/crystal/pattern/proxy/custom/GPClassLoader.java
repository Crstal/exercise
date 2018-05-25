package com.crystal.pattern.proxy.custom;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;

/**
 * @author: Caoyue
 * @Description: 类加载 将class文件加载到jvm虚拟机中
 * @Date: 2018/05/22 20:42
 */
public class GPClassLoader extends ClassLoader {

    public GPClassLoader() {
    }

    // 执行父加载器的loadClass方法 如果向上委托父加载器没有加载成功，则通过findClass(String)查找。
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String className = GPClassLoader.class.getPackage().getName() + "." + name;

        File classFile = new File(GPClassLoader.class.getResource("").getPath().replaceAll("\\.", "/") + name + ".class");
        FileInputStream in = null;
        ByteArrayOutputStream os = null;
        try {
            if (classFile.exists()) {
                in = new FileInputStream(classFile);
                os = new ByteArrayOutputStream();

                byte [] buff = new byte[1024];
                int len;
                while ((len = in.read(buff)) != -1){
                   os.write(buff,0,len);
                }
                //  加载到jvm
                return defineClass(className, os.toByteArray(), 0, os.size());
            }
       } catch (IOException e) {
            e.printStackTrace();
       } finally {
           if(null != in){
               try {
                   in.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }

           if(os != null){
               try {
                   os.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }

       }
        return null;
    }
}
