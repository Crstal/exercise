package com.crystal.serializable;

import lombok.Data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@Data
public class Student extends User implements Serializable {
    private String className;
    private Integer age;
    private transient String hobby;
    // 不被序列化
    private transient String sex;
    // 静态变量不被序列化
    public static Integer num=10;
    // writeObject 和 readObject 方法为什么能够在序列化的时候被调用呢？
    // 因为 ObjectOutputStream 使用了反射来寻找是否声明了这两个方法。ObjectOutputStream 使用 getPrivateMethod，所以这些方法必须声明为 private
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(hobby);
    }
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        hobby = (String) objectInputStream.readObject();
    }
    public String toString() {
        return this.getName() + "--" + this.getAge() + "--" + this.getClassName() + "--" + this.getSex() + "--" + this.getHobby();
    }
}
