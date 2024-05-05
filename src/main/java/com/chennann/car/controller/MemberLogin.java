package com.chennann.car.controller;


import com.chennann.car.service.IMemberLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberLogin {

    @Autowired
    public IMemberLoginService iMemberLoginService;


    @RequestMapping("/user/getOpenId")
    @ResponseBody
    public String getWxOpenId(String code){
        System.out.println(code);
        String openId = iMemberLoginService.getUserOpenId(code);
        return openId;
    }



}
