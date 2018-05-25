package com.crystal.pattern.proxy.statis;

/**
 * @author: Caoyue
 * @Description: 代理类
 * @Date: 2018/05/22 19:09
 */
public class ProxySubject implements Subject {

    private RealSubject subject;

    @Override
    public String read() {
        System.out.println("做些准备");
        String read = subject.read();
        System.out.println("放回远处");
        return read;
    }
}
