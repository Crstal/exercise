package com.crystal.io.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static java.lang.System.out;


public class ByteBufferDemo {

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("E://build.txt");
        FileChannel fileChannel = inputStream.getChannel();

        // 初始化  pos=0 lim=10 cap=10
        ByteBuffer buffer = ByteBuffer.allocate(10);
        out.println("init" + buffer);

        // 读取数据  pos=7 lim=10 cap=10
        fileChannel.read(buffer);
        out.println("read" + buffer);

        // 设置为读取模式，可重复读取 pos=0 lim=7 cap=10
        buffer.flip();
        out.println("flip" + buffer);

        // 获取buffer中数据  pos=7 lim=7 cap=10
        for (int i=0;i<buffer.limit();i++) {
            byte b = buffer.get();
        }
        out.println("get" + buffer);

        // 清空buffer，置为初始化空间  pos=0 lim=7 cap=10
        buffer.clear();
        out.println("clear" + buffer);
    }
}
