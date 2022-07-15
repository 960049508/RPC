package com.lwq.consumer;

import com.lwq.framework.Invocation;
import com.lwq.framework.ProxyFactory;
import com.lwq.framework.protocol.http.HttpClient;
import com.lwq.provider.api.HelloService;

import java.util.*;

public class consumer {
    public static void main(String[] args) {
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String result = helloService.sayHello("lwq,今天学了rpc吗？");
        System.out.println(result);
    }
}
