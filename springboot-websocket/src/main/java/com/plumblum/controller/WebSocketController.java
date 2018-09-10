package com.plumblum.controller;

import com.plumblum.WebSocket.WebSocketDemo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;

/**
 * @Auther: cpb
 * @Date: 2018/9/10 17:42
 * @Description:
 */
@Controller
@RequestMapping("/checkcenter")
public class WebSocketController {

//    页面请求
    @RequestMapping("/socket/{cid}")
    public ModelAndView socket(@PathVariable String cid){
        ModelAndView mav = new ModelAndView("/socket");
        mav.addObject("cid",cid);
        return mav;
    }

    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public String pushToWeb(@PathVariable String cid,String message){
        try {
            WebSocketDemo.sendInfo(message,cid);
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }

}
