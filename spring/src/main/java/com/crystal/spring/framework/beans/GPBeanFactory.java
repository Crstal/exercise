package com.crystal.spring.framework.beans;

/**
 * 单例工厂模式
 */
public interface GPBeanFactory {

    /**
     * 根据 beanName 从 IOC 容器中获取一个实例 bean
     * @param beanName
     * @return
     */
    Object getBean(String beanName);
}
