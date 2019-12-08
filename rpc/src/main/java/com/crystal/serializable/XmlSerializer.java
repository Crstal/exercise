package com.crystal.serializable;

public class XmlSerializer implements ISerializer {
    @Override
    public <T> byte[] serializer(T obj) {
        String xml = XStreamUtil.beanToXml(obj);
        return xml.getBytes();
    }
    @Override
    public <T> T deSerializer(byte[] data, Class<T> clazz) {
        return XStreamUtil.xmlToBean(new String(data), clazz);
    }
}