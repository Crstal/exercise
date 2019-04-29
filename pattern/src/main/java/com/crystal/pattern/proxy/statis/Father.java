package com.crystal.pattern.proxy.statis;

import com.crystal.pattern.proxy.Person;

public class Father implements Person {

    private Person son;

    public Father(Person son) {
        this.son = son;
    }

    @Override
    public String findLove() {
        System.out.println("父母：物色对象");
        System.out.println(son.findLove());
        System.out.println("父母：双方父母同意");
        return "找到了";
    }
}
