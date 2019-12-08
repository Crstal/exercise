package com.crystal.serializable;

import lombok.Data;
import lombok.ToString;

import java.io.*;

@Data
@ToString
public class DeepUser implements Serializable, Cloneable {
    private String name;
    private Email email;
    // 浅克隆
    @Override
    protected User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }
    // 深克隆
    public User deepClone() throws ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this);
            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (User) objectInputStream.readObject();
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
                if (null != byteArrayInputStream) {
                    byteArrayInputStream.close();
                }
                if (null != objectInputStream) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
