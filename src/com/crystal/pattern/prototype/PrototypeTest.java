package com.crystal.pattern.prototype;

import java.util.Date;

/**
 * @author: Caoyue
 * @Description: 测试原型模式
 * @Date: 2018/05/15 19:43
 */
public class PrototypeTest {

    public static void main(String[] args) throws Exception {
        Person p = new Person("lina", 23, new Date());

        // 浅复制
        Person p1 = (Person) p.clone();
        System.out.println(p == p1);
        System.out.println(p.getBirth() == p1.getBirth());

        // 深复制
        Person p2 = p.deepClone(p);
        System.out.println(p == p2);
        System.out.println(p.getBirth() == p2.getBirth());

    }

}
