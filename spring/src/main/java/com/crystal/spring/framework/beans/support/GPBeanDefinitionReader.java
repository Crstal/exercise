package com.crystal.spring.framework.beans.support;

import com.crystal.spring.framework.beans.config.GPBeanDefinition;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GPBeanDefinitionReader {

    private Properties config = new Properties();
    private List<String> registryBeanClasses = new ArrayList<>();

    private static final String SCAN_PACKAGE = "scanPackage";


    public GPBeanDefinitionReader(String... locations) {
        InputStream inputStream = null;
        // 读取配置文件
        try {
            inputStream = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath:", ""));
            config.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        String scanPackage = config.getProperty(SCAN_PACKAGE);
        doScanner(scanPackage);
    }

    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classDir = new File(url.getFile());
        if (classDir != null && classDir.isDirectory()) {
            for (File file : classDir.listFiles()) {
                if (file.isDirectory()) {
                    doScanner(scanPackage + "." + file.getName());
                } else {
                    if (!file.getName().endsWith(".class")) { continue; }
                    String className = scanPackage + "." + file.getName().replace(".class", "");
                    registryBeanClasses.add(className);
                }
            }
        }
    }

    public Properties getConfig() {
        return this.config;
    }

    public List<GPBeanDefinition> loanBeanDefinitions() {
        List<GPBeanDefinition> gpBeanDefinitions = new ArrayList<>();
        for (String className : registryBeanClasses) {
            GPBeanDefinition beanDefinition = doCreateBeanDefinition(className);
            if (beanDefinition != null) {
                gpBeanDefinitions.add(beanDefinition);
            }
        }

        return gpBeanDefinitions;
    }

    private GPBeanDefinition doCreateBeanDefinition(String className) {
        GPBeanDefinition gpBeanDefinition = null;
        try {
            Class<?> beanClass = Class.forName(className);
            if (beanClass.isInterface()) {
                return null;
            }
            gpBeanDefinition = new GPBeanDefinition();
            gpBeanDefinition.setBeanClassName(className);
            gpBeanDefinition.setFactoryBeanName(toFirstLowerCase(beanClass.getSimpleName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gpBeanDefinition;
    }

    private String toFirstLowerCase(String beanName) {
        char[] chars = beanName.toCharArray();
        chars[0] += 32;
        return new String(chars);
    }
}
