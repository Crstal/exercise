package com.crystal.pattern.singleton;

import javafx.concurrent.Task;

/**
 * @author: Caoyue
 * @Description: 任务管理器 （使用单例模式）
 * @Date: 2018/05/15 19:12
 */
public class TaskManage {

    private volatile boolean flag = false;

    private TaskManage() {
        if (flag) {
            throw new RuntimeException("重复初始化！");
        }
        flag = true;
    }

    private static TaskManage instance = null;

    public static TaskManage getInstance() {
        if (instance == null) {
            instance = initInstance();
        }
        return instance;
    }

    private synchronized static TaskManage initInstance() {
        if (instance == null) {
            instance = new TaskManage();
        }
        return instance;
    }

   /* public static TaskManage getInstance() {
        return TaskManageFactory.instance;
    }

    private static class TaskManageFactory {
        private static TaskManage instance = new TaskManage();
    }*/

    public TaskManage readResolve() {
        return instance;
    }
}
