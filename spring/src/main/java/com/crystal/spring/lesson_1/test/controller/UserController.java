package com.crystal.spring.lesson_1.test.controller;

import com.crystal.spring.lesson_1.annotation.Autowired;
import com.crystal.spring.lesson_1.annotation.Controller;
import com.crystal.spring.lesson_1.annotation.RequestMapping;
import com.crystal.spring.lesson_1.annotation.RequestParam;
import com.crystal.spring.lesson_1.test.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("query")
    public void query(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "name") String name) throws IOException {
        response.getWriter().write(userService.getUser(name));
    }
}
