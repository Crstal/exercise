package com.crystal.pattern.delegate;

/**
 * @author: Caoyue
 * @Description: 老板下达任务
 * @Date: 2018/05/29 19:12
 */
public class Boss {

    public static void main(String[] args) {
        new Leader().doSomething("登录");
    }
}
