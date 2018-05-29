package com.crystal.pattern.adapter;

/**
 * 电脑有usb接口，需要兼容2.0,3.0
 */
public class Computer {

    public void usb(IUsb2 usb2) {
        System.out.println("usb 接口");
    }
}
