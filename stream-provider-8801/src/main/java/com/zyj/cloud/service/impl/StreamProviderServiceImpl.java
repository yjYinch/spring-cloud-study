package com.zyj.cloud.service.impl;

import com.zyj.cloud.service.StreamProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

/**
 * @author : zhang yijun
 * @date : 2021/1/29 14:35
 * @description : TODO
 */
@Slf4j
@EnableBinding(Source.class)
public class StreamProviderServiceImpl implements StreamProviderService {

    /**
     * 定义channel
     */
    @Autowired
    private MessageChannel output;

    @Override
    public String send() {
        String message = "hello, this is a message!";
        output.send(MessageBuilder.withPayload(message).build());
        log.info("message: {}", message);
        return null;
    }
}
