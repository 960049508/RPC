package com.lwq.server.apiImpl;


import com.lwq.common.api.GoodbyeService;
import com.lwq.common.api.HelloService;
import com.lwq.common.api.TransformObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GreetServiceImpl implements HelloService, GoodbyeService {
    private static final Logger logger = LoggerFactory.getLogger(GreetServiceImpl.class);

    @Override
    public String sayHello(TransformObject object) {
        logger.info("sayHello:" + object.toString());
        return object.toString();
    }

    @Override
    public String sayGoodbye(TransformObject object) {
        logger.info("sayGoodbye:" + object.toString());
        return object.toString();
    }
}
