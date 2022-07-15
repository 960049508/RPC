package com.lwq.provider;

import com.lwq.framework.protocol.http.HttpServer;
import com.lwq.framework.register.LocalRegister;
import com.lwq.provider.api.HelloService;
import com.lwq.provider.impl.HelloServiceImpl;

public class provider {
    public static void main(String[] args) {
        // 本地注册
        LocalRegister.registered(HelloService.class.getName(), HelloServiceImpl.class);
        // 启动服务，处理请求，响应请求
        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost", 8080);
    }
}
