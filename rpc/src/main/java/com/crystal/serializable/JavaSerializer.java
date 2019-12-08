package com.crystal.serializable;

import java.io.*;

/**
 * @Author: caoyue
 * @Description: java 底层实现方式 序列化和反序列化
 * @Date: 17:59 2019/1/29
 **/
public class JavaSerializer implements ISerializer {
    @Override
    public <T> byte[] serializer(T obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (null != byteArrayOutputStream) {
                    byteArrayOutputStream.close();
                }
                if (null != objectOutputStream) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
    @Override
    public <T> T deSerializer(byte[] bytes, Class<T> clazz) {
        ObjectInputStream objectInputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (T) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (null != objectInputStream) {
                    objectInputStream.close();
                }
                if (null != byteArrayInputStream) {
                    byteArrayInputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}