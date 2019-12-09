package com.crystal.io.bio.tomcat;

import com.crystal.io.bio.tomcat.http.Request;
import com.crystal.io.bio.tomcat.http.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 手写 tomcat
 *      J2EE 标准
 *      Request
 *      Response
 *      Servlet
 *
 *      1. 配置好启动端口  ServerSocket 8080
 *      2. web.xml
 *           servlet-name servlet-class   url-pattern
 *      3. url-pattern 解析和Servlet 建立联系
 *           使用 servletMapping
 *      4. http 请求，发送的数据就是字符串，遵循 HTTP 协议
 *      5. 从协议中拿到 url，对应找到 servlet 用反射实例化
 *      6. 调用对象的 service() 方法，对应执行 doGet doPost 逻辑方法
 *      Request(Inputstream) Response(Outputstream)
 */
public class Tomcat {
    //打开Tomcat源码，全局搜索ServerSocket
    private int port = 8080;

    private Map<String, Servlet> servletMapping = new HashMap<String, Servlet>();
    private Properties webxml = new Properties();

    private ServerSocket serverSocket;

    private void init() {

        //加载web.xml文件,同时初始化 ServletMapping对象
        try {
            String WEB_INF = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(WEB_INF + "web.properties");

            webxml.load(fis);

            for (Object k : webxml.keySet()) {

                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webxml.getProperty(key);
                    String className = webxml.getProperty(servletName + ".className");
                    Servlet obj = (Servlet) Class.forName(className).newInstance();
                    servletMapping.put(url, obj);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        init();
        try {
            serverSocket = new ServerSocket(port);

            listen();
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
                } catch (IOException e) {
                }
            }
        }
    }

    private void process(Socket socket) {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();
            Request request = new Request(is);
            Response response = new Response(os);
            if (servletMapping.containsKey(request.getUrl())) {
                servletMapping.get(request.getUrl()).service(request, response);;
            } else {
                throw new RuntimeException("404 Not Found!");
            }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        new Tomcat().start();
    }
}