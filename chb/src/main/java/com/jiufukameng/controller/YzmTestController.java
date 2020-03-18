package com.jiufukameng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/yzm")
public class YzmTestController {

    @RequestMapping(value = "/cap")
    public String cap(HttpServletRequest request, HttpServletResponse response) {
        return "/yzm/cap";
    }

    @ResponseBody
    @RequestMapping(value = "/validate")
    public String validate(HttpServletRequest request, HttpServletResponse response, String yzmCode) {

        System.out.println("用户输入的验证码为yzmCode=" + yzmCode);
        String yzm = (String) request.getSession().getAttribute("TEST_YZM");
        String prin = "";
        if (!StringUtils.equals(yzm, yzmCode)) {
            prin = "##########################################验证码输入错误############################################";
        } else {
            prin = "******************************************验证码输入成功********************************************";
        }
        System.out.println(prin);
        return prin;
    }
}
