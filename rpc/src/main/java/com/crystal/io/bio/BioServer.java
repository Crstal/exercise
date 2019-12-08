package com.crystal.io.bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {
    private int port;
    private ServerSocket serverSocket;

    public BioServer(int port) {
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        System.out.println("listen on " + port);
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                process(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {}
            }
        }
    }

    private void process(Socket socket) {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("接收到消息：" + inputStream.readObject());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject("server ok");
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
        }

    }

    public static void main(String[] args) {
        BioServer bioServer = new BioServer(8080);
        bioServer.listen();
    }
}
