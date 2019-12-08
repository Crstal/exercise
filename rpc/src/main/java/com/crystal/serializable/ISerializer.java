package com.crystal.serializable;

public interface ISerializer {
    /**
     * 序列化
     */
    <T> byte[] serializer(T obj);
    /**
     * 反序列化
     */
    <T> T deSerializer(byte[] data, Class<T> clazz);
}