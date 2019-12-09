package com.crystal.io.bio.tomcat.http;

import java.io.IOException;
import java.io.OutputStream;

public class Response {
    private OutputStream outputStream;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String sb) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html;\n")
                .append("\r\n")
                .append(sb);
        try {
            outputStream.write(buffer.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
