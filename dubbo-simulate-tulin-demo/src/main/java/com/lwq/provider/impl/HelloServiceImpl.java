package com.lwq.provider.impl;

import com.lwq.provider.api.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello!" + name;
    }
}
