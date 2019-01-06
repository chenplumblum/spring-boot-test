package com.plumblum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmailController {
    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping("/email")
    @ResponseBody
    public String sendEmail(){
        //1.新建邮件消息
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //2.初始化邮件内容
        simpleMailMessage.setFrom("15602335233@163.com");
        simpleMailMessage.setTo("15602335233@163.com");
        simpleMailMessage.setSubject("test email");
        simpleMailMessage.setText("测试邮箱信息");
        //3.发送邮箱
        javaMailSender.send(simpleMailMessage);
        return "ok";

    }
}
