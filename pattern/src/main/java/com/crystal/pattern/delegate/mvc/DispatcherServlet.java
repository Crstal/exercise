package com.crystal.pattern.delegate.mvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @Author: caoyue
* @Description: 
* @Date: 21:06 2019/3/13
**/
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().contains("getOrder")) {
            new OrderController().getOrder("");
        } else if (req.getRequestURI().contains("getUser")) {
            new UserController().getUser();
        } else {
            throw new RuntimeException("404 没有找到访问的地址");
        }

    }
}
