package com.crystal.java8.defaultmethod;

public class Car implements Vehicle, FourWheeler {
    @Override
    public void print() {
        Vehicle.super.print();
        FourWheeler.super.print();
        Vehicle.blowHorn();
        System.out.println("我是一辆四轮汽车!");
    }

    public static void main(String[] args) {
        Car car = new Car();
        car.print();
    }
}
