package com.crystal.pattern.proxy.cglib;

public class ProxyTest {
    public static void main(String[] args) {
        ProxyMeipo meipo = new ProxyMeipo();
        Barry barry = (Barry) meipo.getInstance(new Barry());
        System.out.println(barry.findLove());
        System.out.println(barry.getClass().getSuperclass());
    }
}
