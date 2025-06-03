package com.imooc.youtube.service.websocket;

import com.alibaba.fastjson.JSONObject;
import com.imooc.youtube.domain.Danmu;
import com.imooc.youtube.domain.constant.UserMomentsConstant;
import com.imooc.youtube.service.DanmuService;
import com.imooc.youtube.service.util.RocketMQUtil;
import com.imooc.youtube.service.util.TokenUtil;
import io.netty.util.internal.StringUtil;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@ServerEndpoint("/imserver/{token}")
public class WebSocketService {

    private final Logger logger =  LoggerFactory.getLogger(this.getClass());

    private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);

    public static final ConcurrentHashMap<String, WebSocketService> WEBSOCKET_MAP = new ConcurrentHashMap<>();

    private Session session;

    private String sessionId;

    private Long userId;

    private static ApplicationContext APPLICATION_CONTEXT;

    /**
     * Inject the Spring application context.
     * Needed because WebSocket endpoints are not managed by Spring by default.
     */
    public static void setApplicationContext(ApplicationContext applicationContext){
        WebSocketService.APPLICATION_CONTEXT = applicationContext;
    }

    /**
     * Triggered when a new WebSocket connection is established.
     * Parses the token to get userId, tracks session, and updates online count.
     */
    @OnOpen
    public void openConnection(Session session, @PathParam("token") String token){
        try{
            this.userId = TokenUtil.verifyToken(token);
        }catch (Exception ignored){}
        this.sessionId = session.getId();
        this.session = session;
        if(WEBSOCKET_MAP.containsKey(sessionId)){
            WEBSOCKET_MAP.remove(sessionId);
            WEBSOCKET_MAP.put(sessionId, this);
        }else{
            WEBSOCKET_MAP.put(sessionId, this);
            ONLINE_COUNT.getAndIncrement();
        }
        logger.info("user connect successfully" + sessionId + ", users online:" + ONLINE_COUNT.get());
        try{
            this.sendMessage("0");
        }catch (Exception e){
            logger.error("connect exception.");
        }
    }

    /**
     * Triggered when a WebSocket connection is closed.
     * Removes the session from the map and updates online user count.
     */
    @OnClose
    public void closeConnection(){
        if(WEBSOCKET_MAP.containsKey(sessionId)){
            WEBSOCKET_MAP.remove(sessionId);
            ONLINE_COUNT.getAndDecrement();
        }
        logger.info("user exit" + sessionId + "users online:" + ONLINE_COUNT.get());
    }

    /**
     * Triggered when a message is received from the client.
     * - Sends the message asynchronously to RocketMQ
     * - Persists the danmu to DB and Redis if userId is available
     */
    @OnMessage
    public void onMessage(String message){
        logger.info("user info:" + sessionId + "message:" + message);
        if(!StringUtil.isNullOrEmpty(message)){
            try{

                for(Map.Entry<String, WebSocketService> entry : WEBSOCKET_MAP.entrySet()){
                    WebSocketService webSocketService = entry.getValue();
                    DefaultMQProducer danmusProducer = (DefaultMQProducer)APPLICATION_CONTEXT.getBean("danmusProducer");
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("message", message);
                    jsonObject.put("sessionId", webSocketService.getSessionId());
                    Message msg = new Message(UserMomentsConstant.TOPIC_DANMUS, jsonObject.toJSONString().getBytes(StandardCharsets.UTF_8));
                    RocketMQUtil.asyncSendMsg(danmusProducer, msg);
                }
                if(this.userId != null){

                    Danmu danmu = JSONObject.parseObject(message, Danmu.class);
                    danmu.setUserId(userId);
                    danmu.setCreateTime(new Date());
                    DanmuService danmuService = (DanmuService)APPLICATION_CONTEXT.getBean("danmuService");
                    danmuService.asyncAddDanmu(danmu);

                    danmuService.addDanmusToRedis(danmu);
                }
            }catch (Exception e){
                logger.error("Danmu exception.");
                e.printStackTrace();
            }
        }
    }
    /**
     * Triggered when a WebSocket error occurs.
     * You should log or handle the error here.
     */
    @OnError
    public void onError(Throwable error){
        logger.error("WebSocket error occurred", error);
    }


    /**
     * Sends a message to the connected client.
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * Periodic task that sends the number of online users to all connected clients.
     */
    @Scheduled(fixedRate=5000)
    private void noticeOnlineCount() throws IOException {
        for(Map.Entry<String, WebSocketService> entry : WebSocketService.WEBSOCKET_MAP.entrySet()){
            WebSocketService webSocketService = entry.getValue();
            if(webSocketService.session.isOpen()){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("onlineCount", ONLINE_COUNT.get());
                jsonObject.put("msg", "user online:" + ONLINE_COUNT.get());
                webSocketService.sendMessage(jsonObject.toJSONString());
            }
        }
    }

    /**
     * Returns the current WebSocket session.
     */
    public Session getSession() {
        return session;
    }

    /**
     * Returns the session ID.
     */
    public String getSessionId() {
        return sessionId;
    }
}
