package com.lwq.framework;

import com.lwq.framework.protocol.http.HttpClient;
import com.lwq.provider.api.HelloService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    public static <T> T getProxy(Class interfaceClass) {
        // 用户配置 （代理方式） jdk\cglib
        Object proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // System.out.println(interfaceClass == method.getDeclaringClass()); // true
                Invocation invocation = Invocation.builder()
                        .interfaceName(method.getDeclaringClass().getName()) // method.getDeclaringClass()
                        .methodName(method.getName())
                        .params(args)
                        .paramType(method.getParameterTypes())
                        .build();
                HttpClient httpClient = new HttpClient();
                String result = httpClient.send("localhost", 8080, invocation);
                return result;
            }
        });
        return (T) proxyInstance;
    }
}
