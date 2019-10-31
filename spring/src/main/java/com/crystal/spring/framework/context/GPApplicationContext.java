package com.crystal.spring.framework.context;

import com.crystal.spring.framework.beans.GPBeanFactory;
import com.crystal.spring.framework.beans.config.GPBeanDefinition;
import com.crystal.spring.framework.beans.support.GPBeanDefinitionReader;
import com.crystal.spring.framework.beans.support.GPDefaultListableBeanFactory;

import java.util.List;

public class GPApplicationContext extends GPDefaultListableBeanFactory implements GPBeanFactory {

    private String[] configLocations;
    private GPBeanDefinitionReader reader;

    public GPApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
    }

    @Override
    public Object getBean(String beanName) {
        return null;
    }

    @Override
    protected void refresh() {
        // 1.定位
        GPBeanDefinitionReader reader = new GPBeanDefinitionReader(this.configLocations);

        // 2.加载
        List<GPBeanDefinition> beanDefinitionList = reader.loanBeanDefinitions();

        // 3.注册
        doRegisterBeanDefinition(beanDefinitionList);

        // 4.初始化不是延迟加载的类
        doAutowrid();
    }

    private void doRegisterBeanDefinition(List<GPBeanDefinition> beanDefinitionList) {
        for (GPBeanDefinition beanDefinition: beanDefinitionList) {
            super.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
        }
    }

    private void doAutowrid() {
    }
}
