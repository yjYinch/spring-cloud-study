package com.zyj.cloud.controller;

import com.zyj.cloud.service.StreamProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhang yijun
 * @date : 2021/1/29 14:48
 * @description : TODO
 */

@RestController
public class SendMessageController {

    @Autowired
    private StreamProviderService service;

    @GetMapping("/send")
    public String sendMessage(){
        return service.send();
    }
}
