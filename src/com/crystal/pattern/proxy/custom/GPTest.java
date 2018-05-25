package com.crystal.pattern.proxy.custom;

import com.crystal.pattern.proxy.jdk.Lina;
import com.crystal.pattern.proxy.jdk.Person;
import com.crystal.pattern.proxy.jdk.ProxyMeipo;

public class GPTest {

    public static void main(String[] args) {
        GPProxyMeipo meipo = new GPProxyMeipo();
        Person person = (Person) meipo.getInstance(new Lina());
        person.findLove();
        System.out.println(person.getClass());
    }
}
