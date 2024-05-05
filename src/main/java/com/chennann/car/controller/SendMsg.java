package com.chennann.car.controller;


import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@RestController
public class SendMsg {

    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.secret}")
    private String secret;

    @Value("${wx.openid}")
    private String openid;

    @Value("${wx.templateId}")
    private String templateId;

    @GetMapping("/sendMsg")
    public void sendMsg(){
        //1:获取token（接口调用凭证）
        String token = queryToken();
        //2:发送订阅消息
        send(token);
    }

    // 1: 获取 access_token  (2h过期)
    public String queryToken(){
        String tokenUrl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
        tokenUrl = tokenUrl + "&appid=" + appId + "&secret=" + secret;
        String result = HttpUtil.get(tokenUrl);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        String token = jsonObject.get("access_token").toString();
        return token;
    }

    public HashMap<String, Object> formatParam(String value, String type) {
        HashMap<String, Object> param = new HashMap<>();
        if ("date".equals(type)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");  // 按微信要求的格式设定
            param.put("value", sdf.format(new Date(Long.parseLong(value))));
        } else {
            param.put("value", value);
        }
        return param;
    }



    public void send(String token){
        String msgUrl="https://api.weixin.qq.com/cgi-bin/message/subscribe/send";
        msgUrl = msgUrl + "?access_token=" + token;
        // 设置模板参数
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("touser", openid);                 // 接收方
        paramMap.put("template_id", templateId);        // 模板id
        paramMap.put("page","pages/self/self");         // 消息中要跳转的页面
        // 设置data 模板内容
//        HashMap<String, Object> data = new HashMap<>();
//        data.put("thing1", formatParam("11111"));
//        data.put("time2", formatParam("22222"));
//        data.put("thing3", formatParam("33333"));
//        data.put("thing4", formatParam("44444"));
//        data.put("amount5", formatParam("123.23"));
//        paramMap.put("data", data);


        // 然后在数据构建时
        HashMap<String, Object> data = new HashMap<>();
        long currentTimeMillis = System.currentTimeMillis();
        String timestamp = String.valueOf(currentTimeMillis);
        data.put("thing1", formatParam("chennann064", "text"));
        data.put("time2", formatParam(timestamp, "date"));  // 假设这是时间戳
        data.put("thing3", formatParam("结算", "text"));
        data.put("thing4", formatParam("您的车辆已经完成维修，请到店缴费提车🚀", "text"));
        data.put("amount5", formatParam("987.66", "number"));
        paramMap.put("data", data);



        // 转json字符串
        System.out.println(paramMap);
        String jsonObject = JSONUtil.toJsonStr(paramMap);
        String result= HttpUtil.post(msgUrl, jsonObject);
        System.out.println(result);
    }

    public HashMap<String, Object> formatParam(String value){
        HashMap<String, Object> data = new HashMap<>();
        data.put("value", value);
        return data;
    }





}
