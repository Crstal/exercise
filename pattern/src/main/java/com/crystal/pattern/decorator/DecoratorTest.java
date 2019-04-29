package com.crystal.pattern.decorator;

/**
 * @author: Caoyue
 * @Description: 测试类
 * @Date: 2018/06/05 16:42
 */
public class DecoratorTest {
    public static void main(String[] args) {
        Sourceable source = new Source();
        Sourceable obj = new Decorator(source);
        obj.method();
    }
}
