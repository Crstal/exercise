package com.crystal.serializable;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;

public class SerializerDemo {

    @Test
    public void protobufTest() throws InvalidProtocolBufferException {
        UserProtos.User user = UserProtos.User.newBuilder().setName("lina").setAge(12).build();
        ByteString byteString = user.toByteString();    // 序列化
        UserProtos.User user1 = UserProtos.User.parseFrom(byteString); // 反序列化

        System.out.println(byteString.size());
        System.out.println(user1);
        // 转换成字节数字
        byte[] bytes=user.toByteArray();
        for(byte bt:bytes){
            System.out.print(bt+" ");
        }
    }
}
