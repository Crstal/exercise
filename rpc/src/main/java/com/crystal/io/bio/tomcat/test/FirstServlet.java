package com.crystal.io.bio.tomcat.test;

import com.crystal.io.bio.tomcat.http.HttpServlet;
import com.crystal.io.bio.tomcat.http.Request;
import com.crystal.io.bio.tomcat.http.Response;

public class FirstServlet extends HttpServlet {

    @Override
    public void doGet(Request request, Response response) throws Exception {
        doPost(request, response);
    }

    @Override
    public void doPost(Request request, Response response) throws Exception {
        response.write("this is the first servlet");
    }
}
