package com.crystal.thread.processor;

public class PrevProcessor extends AbstractProcessor {


    public PrevProcessor(IProcessor processor) {
        setNextProcessor(processor);
    }

    @Override
    public boolean doRequest(Request request) {
        System.out.println("PrevProcessor... do something, " + request.getMessage());
        return true;
    }
}
