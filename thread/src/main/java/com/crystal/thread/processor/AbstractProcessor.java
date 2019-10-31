package com.crystal.thread.processor;

import java.util.concurrent.LinkedBlockingDeque;

public abstract class AbstractProcessor extends Thread implements IProcessor {


    private LinkedBlockingDeque<Request> requests = new LinkedBlockingDeque<>();

    private IProcessor nextProcessor;

    private volatile Boolean isRun = false;

    private volatile Boolean isStop = false;


    public AbstractProcessor() {}

    public AbstractProcessor(IProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void run() {
        while (!isStop) {
            try {
                Request request = requests.take();
                // 真实的处理逻辑
                boolean result = doRequest(request);
                // 交给下一个处理器处理
                if (result && nextProcessor != null) {
                    nextProcessor.processor(request);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void processor(Request request) {
        // 添加请求任务
        addRequestToQueue(request);
        // 启动线程
        if (!isRun){
            synchronized (this){
                if (!isRun){
                    super.start();
                    isRun = true;
                }
            }
        }
    }

    /**
     * 防止子类开启线程，有父类维护，使用时启动任务线程
     */
    @Override
    public synchronized void start() {
        // 关闭入口
        throw new UnsupportedOperationException("自动启动线程，无需手动开启");
    }

    public void setStop(Boolean stop) {
        isStop = stop;
    }

    public void setNextProcessor(IProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    /**
     * @Author crystal
     * @Description 子类处理请求
     * @Date 2019-10-30
     * @Param
     * @return
    */
    public abstract boolean doRequest(Request request);

    /**
     * @Author crystal
     * @Description 添加到责任链内
     * @Date 2019-10-30
     * @Param
     * @return
    */
    public void addRequestToQueue(Request request) {
        requests.add(request);
    }
}
