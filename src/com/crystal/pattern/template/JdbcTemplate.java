package com.crystal.pattern.template;

import java.util.List;

/**
 * @author: Caoyue
 * @Description: 抽象模板父类
 * @Date: 2018/05/26 15:44
 */
public abstract class Template {

    public abstract List<?> executeQuery(String sql);
}
