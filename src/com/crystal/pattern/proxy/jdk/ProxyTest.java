package com.crystal.pattern.proxy.jdk;

public class ProxyTest {

    public static void main(String[] args) throws Exception {
        ProxyMeipo meipo = new ProxyMeipo();
        Person person = (Person) meipo.getInstance(new Lina());
        person.findLove();
        System.out.println(person.getClass());
    }
}
