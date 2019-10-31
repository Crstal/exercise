package com.crystal.java8.method;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MethodTest {

    @Test
    public void methodTest() {
        // 构造器引用
        Car car = Car.create(Car::new);
        List<Car> cars = Arrays.asList(car);

        // 静态方法引用
        cars.forEach(Car::collide);

        // 特定类的任意类的参数调用
        cars.forEach(Car::repair);

        // 特定对象的方法引用
        cars.forEach(car::follow);
    }
}
