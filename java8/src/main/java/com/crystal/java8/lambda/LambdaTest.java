package com.crystal.java8.lambda;

import org.junit.Test;

public class LambdaTest {

    User crystal = new User("crystal");

    @Test
    public void variableTest() {
//        User crystal = new User("crystal");
        Print print = () -> {
//            User crystal = new User("crystal");  // 编译异常
//            crystal = new User("lina");   // 编译异常
            System.out.println(crystal);
            System.out.println(this.toString());
        };
        print.print();
    }
    
    @Test
    public void threadTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("do something...");
            }
        }).start();

        new Thread(() -> {System.out.println("do something");}).start();
    }

    @Test
    public void simpleTest() {

        // 指定参数类型
        MathOperation addition = (int a, int b) -> a+b;

        // 不指定参数类型
        MathOperation subtraction = (a, b) -> a-b;

        // 指明返回值
        MathOperation multiplication = (a, b) -> {return a*b;};

        // 默认返还
        MathOperation division  = (int a, int b) -> a/b;

        System.out.println("20+2=" + addition.operate(20, 2));
        System.out.println("20-2=" + subtraction.operate(20, 2));
        System.out.println("20*2=" + multiplication.operate(20, 2));
        System.out.println("20/2=" + division.operate(20, 2));
    }

    interface MathOperation {
        int operate(int a, int b);
    }

    interface Print{
        void print();
    }

    @Override
    public String toString() {
        return "LambdaTest{" +
                "crystal=" + crystal +
                '}';
    }
}
