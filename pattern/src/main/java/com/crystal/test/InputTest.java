package com.crystal.test;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class InputTest {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedInputStream = new BufferedReader(new FileReader("D:/new.txt"));
        Set<String> orders = new HashSet<>();
        String orderId = null;
        while ((orderId = bufferedInputStream.readLine()) != null) {
            orders.add(orderId);
        }
        bufferedInputStream.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter("D:/new1.txt"));
        for (String o : orders) {
            writer.write(o+",");
        }
        writer.flush();
        writer.close();
    }
}
