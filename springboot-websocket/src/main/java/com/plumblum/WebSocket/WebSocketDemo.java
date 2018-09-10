package com.plumblum.WebSocket;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;


/**
 * @Auther: cpb
 * @Date: 2018/9/10 17:02
 * @Description:
 */
@Component
@ServerEndpoint(value = "/websocket")
public class WebSocketDemo {
    static Logger log  = LoggerFactory.getLogger(WebSocketDemo.class);

//    统计当前在线数
     private static int ONLINE_COUNT = 0;
//     存放每个客户端对应的webSocket对象
    private static CopyOnWriteArraySet<WebSocketDemo> webSocketDemos = new CopyOnWriteArraySet<>();

//    与某个客户端进行连接通话，通过session进行发送数据
    private Session session;

//    接受参数（sid）
    private String sid = "";

    public static int getOnlineCount() {
        return ONLINE_COUNT;
    }

    public static void setOnlineCount(int onlineCount) {
        ONLINE_COUNT = onlineCount;
    }
    public static synchronized void subOnlineCount() {
        WebSocketDemo.ONLINE_COUNT--;
    }
    public static synchronized void addOnlineCount() {
        WebSocketDemo.ONLINE_COUNT++;
    }
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    //    连接建立成功的调用的方法
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid){
        this.session = session;
//        加入set
        webSocketDemos.add(this);
//        在线人数加1
        log.info("当前人数为"+getOnlineCount());
        this.sid = sid;

        try {
            sendMessage("连接成功");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

//    连接关闭方法
    @OnClose
    public void onClosed(){
//        从set中删除
        webSocketDemos.remove(this);
//        在线人数减去1
        subOnlineCount();
        log.info("当前在线人数为："+getOnlineCount());
    }

//    接受客户端信息方法
    @OnMessage
    public void onMessage(String message,Session session){
        log.info("收到来自窗口"+sid+"的信息："+message);
//        群发信息
        for(WebSocketDemo webSocketDemo:webSocketDemos){
            try {
                webSocketDemo.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


//    发送错误处理方法
    @OnError
    public void onError(Session session,Throwable error){
        log.error("发送错误");
        error.printStackTrace();
    }
//    服务器发送信息方法
        public void sendMessage(String message) throws IOException {
            this.session.getBasicRemote().sendText(message);
        }

//    自定义（群发信息）
    public static void sendInfo(String message,@PathParam("sid") String sid){
        log.info("推送信息到窗口："+sid+",内容为"+message);
        for(WebSocketDemo item:webSocketDemos){
            try {
                if (sid ==null) {
                    item.sendMessage(message);
                } else if(item.sid.equals(sid)){
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

