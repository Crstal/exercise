package com.crystal.thread.processor;

public class PrintProcessor extends AbstractProcessor {

    public PrintProcessor() {}

    public PrintProcessor(IProcessor processor) {
        super(processor);
    }

    @Override
    public boolean doRequest(Request request) {
        System.out.println("PrintProcessor... do something, " + request.getMessage());
        return true;
    }
}


