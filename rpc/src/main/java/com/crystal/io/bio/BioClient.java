package com.crystal.io.bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class BioClient {

    public static void main(String[] args) {
        Socket socket = null;
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            socket = new Socket("localhost", 8080);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject("crystal");
            System.out.println("client write crystal");
            inputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("return " + inputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {}
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {}
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {}
            }
        }
    }
}
