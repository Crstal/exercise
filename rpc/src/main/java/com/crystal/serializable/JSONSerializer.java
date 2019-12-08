package com.crystal.serializable;

import com.alibaba.fastjson.JSON;

public class JSONSerializer implements ISerializer {
    @Override
    public <T> byte[] serializer(T obj) {
        return JSON.toJSONString(obj).getBytes();
    }
    @Override
    public <T> T deSerializer(byte[] data, Class<T> clazz) {
        return JSON.parseObject(new String(data), clazz);
    }
}
