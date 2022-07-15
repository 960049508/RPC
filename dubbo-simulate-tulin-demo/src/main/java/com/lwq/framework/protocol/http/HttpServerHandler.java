package com.lwq.framework.protocol.http;

import com.lwq.framework.Invocation;
import com.lwq.framework.register.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;

public class HttpServerHandler {
    public void handler(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Invocation invocation = (Invocation) new ObjectInputStream(req.getInputStream()).readObject();
            String interfaceName = invocation.getInterfaceName();
            Class clazz = LocalRegister.get(interfaceName); // 具体实现类
            Method method = clazz.getMethod(invocation.getMethodName(), invocation.getParamType()); // 获得需要执行的方法
            // 反射执行
            Object result = method.invoke(clazz.newInstance(), invocation.getParams());
            IOUtils.write((String) result, resp.getOutputStream()); // 响应结果
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
