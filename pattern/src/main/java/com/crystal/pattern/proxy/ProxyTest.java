package com.crystal.pattern.proxy;

import com.crystal.pattern.proxy.cglib.Barry;
import com.crystal.pattern.proxy.custom.GPProxyMeipo;
import com.crystal.pattern.proxy.jdk.Lina;
import com.crystal.pattern.proxy.jdk.ProxyMeipo;
import com.crystal.pattern.proxy.statis.Father;
import com.crystal.pattern.proxy.statis.Son;
import net.sf.cglib.core.DebuggingClassWriter;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

public class ProxyTest {

    @Test
    public void staticTest() {
        Person son = new Son();
        Person father = new Father(son);
        System.out.println(father.findLove());;
    }

    @Test
    public void jdkTest() throws Exception {
        ProxyMeipo meipo = new ProxyMeipo();
        Person person = (Person) meipo.getInstance(new Lina());
        person.findLove();

        //通过反编译工具可以查看源代码
        byte [] bytes = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{Person.class});
        FileOutputStream os = new FileOutputStream("D://$Proxy0.class");
        os.write(bytes);
        os.close();

        System.out.println(person.getClass());
    }

    @Test
    public void gpJdkTest() throws Exception {
        GPProxyMeipo meipo = new GPProxyMeipo();
        Person person = (Person) meipo.getInstance(new Lina());
        person.findLove();
        System.out.println(person.getClass());
    }

    @Test
    public void cglibTest() {
        // 利用 cglib 的代理类可以将内存中的 class 文件写入本地磁盘
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D://cglib_proxy_class");

        com.crystal.pattern.proxy.cglib.ProxyMeipo meipo = new com.crystal.pattern.proxy.cglib.ProxyMeipo();
        Barry barry = (Barry) meipo.getInstance(new Barry());
        System.out.println(barry.findLove());
        System.out.println(barry.getClass().getSuperclass());
    }
}
