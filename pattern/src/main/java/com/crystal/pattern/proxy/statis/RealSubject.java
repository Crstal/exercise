package com.crystal.pattern.proxy.statis;

/**
 * @author: Caoyue
 * @Description: 真正调用的类
 * @Date: 2018/05/22 19:09
 */
public class RealSubject implements Subject {
    @Override
    public String read() {
        return "撒哈拉沙漠";
    }
}
