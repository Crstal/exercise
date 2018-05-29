package com.crystal.pattern.delegate;

/**
 * @author: Caoyue
 * @Description: 小组成员A 真正做事的人
 * @Date: 2018/05/29 19:11
 */
public class TargetA implements ITarget {
    @Override
    public void doSomething(String command) {
        System.out.println("我是员工A，现在开始干" + command + "");
    }
}
