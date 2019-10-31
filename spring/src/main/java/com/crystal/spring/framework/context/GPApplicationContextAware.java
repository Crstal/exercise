package com.crystal.spring.framework.context;

/**
 * 通过解耦的方式获取IOC容器的顶层设计
 * 通过监听器去扫描所有的类，只要是实现了该接口将调用 setApplicationContext() 方法，从而将 IOC 容器注入到目标对象中
 */
public interface GPApplicationContextAware {

    void setApplicationContext();
}
