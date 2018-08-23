package com.plumblum.controller;

import com.plumblum.util.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Controller
public class HomeController {
    @RequestMapping({"/","/index"})
    public String index(){
        return "/index";
    }

    @RequestMapping("/login")
    public String login(){
        return "/login";
    }

    @RequestMapping("/sys/login")
    @ResponseBody
    public static R login(@RequestParam String username, String password,boolean rememberMe) throws IOException {


        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(rememberMe);
            subject.login(token);
        } catch (UnknownAccountException e) {
            return R.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return R.error("账号或密码不正确");
        } catch (LockedAccountException e) {
            return R.error("账号已被锁定,请联系管理员");
        } catch (AuthenticationException e) {
            return R.error("用户密码错误超过三次");
        }

//        SecurityUtils.getSubject().logout();
        return R.ok();
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }

    /**
     * 退出
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:login.html";
    }
}