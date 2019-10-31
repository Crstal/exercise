package com.crystal.thread.processor;

/**
 * @Author crystal
 * @Description 责任链测试
 * @Date 2019-10-30
 * @Param 
 * @return 
*/
public class Test {

    IProcessor processor;

    public void setUp() {
        SaveProcessor saveProcessor = new SaveProcessor();
        PrintProcessor printProcessor = new PrintProcessor(saveProcessor);
        processor = new PrevProcessor(printProcessor);
    }

    @org.junit.Test
    public void testProcessor() {
        setUp();
        Request request = new Request();
        request.setMessage("Crystal");
        processor.processor(request);
    }
}
