package com.crystal.io.bio.tomcat.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class Request {

    private String uri;
    private String method;
    private InputStream inputStream;

    public Request(InputStream is) {
        this.inputStream = is;
        String content = "";
        byte[] bytes = new byte[1024];
        int len = 0;
        try {
            while ((len = is.read(bytes)) > 0) {
                content += new String(bytes);
            }

            String line = content.split("\\n")[0];
            String[] arr = line.split("\\s");

            this.method = arr[0];
            this.uri = arr[1].split("\\?")[0];
            System.out.print(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return uri;
    }

    public String getMethod() {
        return method;
    }

    public Map<String, List<String>> getParameters() {
        return null;
    }

    public String getParameter(String name) {
        Map<String, List<String>> params = getParameters();
        List<String> param = params.get(name);
        if (null == param) {
            return null;
        } else {
            return param.get(0);
        }
    }
}
