package com.crystal.pattern.decorator;

/**
 * @author: Caoyue
 * @Description: 装饰类
 * @Date: 2018/06/05 16:42
 */
public class Decorator implements Sourceable {

    private Sourceable source;

    public Decorator(Sourceable source){
        super();
        this.source = source;
    }
    @Override
    public void method() {
        System.out.println("before decorator!");
        source.method();
        System.out.println("after decorator!");
    }
}
