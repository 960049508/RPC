package com.lwq.client;

import com.lwq.common.api.GoodbyeService;
import com.lwq.common.api.TransformObject;
import com.lwq.common.api.HelloService;

public class TestClient1 {
    public static void main(String[] args) {
        RpcProxy proxy = new RpcProxy("127.0.0.1", 9001);
        GoodbyeService goodbyeService = proxy.getProxy(GoodbyeService.class);
        TransformObject object = new TransformObject(1, "This is client1");
        String res = goodbyeService.sayGoodbye(object);
        System.out.println(res);
        while (true) ;
    }
}
