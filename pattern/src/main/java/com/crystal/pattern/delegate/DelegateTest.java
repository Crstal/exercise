package com.crystal.pattern.delegate;

/**
* @Author: caoyue
* @Description: 委派模式测试
 * Delegate 结尾 或者 Dispatcher 都是委派
* @Date: 20:46 2019/3/13
**/
public class DelegateTest {

    public static void main(String[] args) {
        new Boss().doSomething("登录", new Leader());
    }
}
