package com.crystal.pattern.delegate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Caoyue
 * @Description: 项目经理 根据一定规则分配任务
 * @Date: 2018/05/29 19:10
 */
public class Leader implements ITarget {

    private Map<String, ITarget> targets = new HashMap<>();

    /**
     * 项目经理持有小组成员可供选择，类似策略模式
     */
    public Leader() {
        targets.put("加密", new TargetA());
        targets.put("登录", new TargetB());
    }

    public void doSomething(String command) {
        targets.get(command).doSomething(command);
    }
}
