package com.lwq.client;

import com.lwq.common.api.HelloService;
import com.lwq.common.api.TransformObject;

public class TestClient {
    public static void main(String[] args) {
        RpcProxy proxy = new RpcProxy("127.0.0.1", 9001);
        HelloService helloService = proxy.getProxy(HelloService.class);
        TransformObject object = new TransformObject(0, "This is client");
        String res = helloService.sayHello(object);
        System.out.println(res);
        while (true) ;
    }
}
