package com.crystal.thread.processor;

public class SaveProcessor extends AbstractProcessor {

    public SaveProcessor() {}

    public SaveProcessor(IProcessor processor) {
        setNextProcessor(processor);
    }

    @Override
    public boolean doRequest(Request request) {
        System.out.println("SaveProcessor... do something, " + request.getMessage());
        return true;
    }
}
