package com.zyj.cloud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author : zhang yijun
 * @date : 2021/1/29 15:06
 * @description : TODO
 */

@Slf4j
@Component
@EnableBinding(Sink.class)
public class ConsumerMessage {

    @Value("${server.port}")
    private Integer port;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        log.info("端口号：{}, 接收到的消息：{}", port, message.getPayload());
    }
}
