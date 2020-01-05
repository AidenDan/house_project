package com.controller;

import com.utils.HttpClientUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2020-1-5 18:07:25
 */
@Controller
@RequestMapping("/sms")
public class SmsController {

    @RequestMapping("/send")
    @ResponseBody
    public Map<String, Object> send(String phone, HttpSession session){
        System.out.println("1234");
        Map<String, Object> map = new HashMap<>();
        //用户名
        String Uid = "aiden";

        //接口安全秘钥
        String Key = "d41d8cd98f00b204e980";

        //手机号码，多个号码如13800000000,13800000001,13800000002
       String smsMob = phone;

        int code = (int)(Math.random() * (8999) + 1000);
        session.setMaxInactiveInterval(60*3);
        session.setAttribute("code0", 0000);
        //短信内容
        String smsText = "验证码为："+code;
        HttpClientUtil client = HttpClientUtil.getInstance();

        //UTF发送
        int result = client.sendMsgUtf8(Uid, Key, smsText, smsMob);
        if(result>0){
            map.put("result", 1);
        }else{
            map.put("result", -1);
        }
        return map;
    }

    @RequestMapping("/login")
    public String login(String code, HttpSession session){
        int code0 = (int)session.getAttribute("code0");
        if (code.equals(code0+"")){
            return "/page/timeout.html";
        }else
            return "/page/err.jsp";
    }
}
