package com.crystal.io.bio.tomcat.http;

import com.crystal.io.bio.tomcat.Servlet;

public abstract class HttpServlet extends Servlet {

    public final void service(Request request,Response response) throws Exception {

        //由service方法来决定，是调用doGet或者调用doPost
        if("GET".equalsIgnoreCase(request.getMethod())){
            doGet(request, response);
        }else{
            doPost(request, response);
        }

    }

    public abstract void doGet(Request request,Response response) throws Exception;

    public abstract void doPost(Request request,Response response) throws Exception;
}
