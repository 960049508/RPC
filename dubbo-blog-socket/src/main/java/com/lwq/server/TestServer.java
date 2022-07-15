package com.lwq.server;

import com.lwq.common.api.HelloService;
import com.lwq.server.apiImpl.GreetServiceImpl;
import com.lwq.server.register.LocalRegistry;

public class TestServer {
    public static void main(String[] args) {
        GreetServiceImpl service = new GreetServiceImpl();
        LocalRegistry.register(service);
        RpcServer rpcServer = new RpcServer();
        rpcServer.start(9001);
    }
}
