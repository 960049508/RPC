package com.lwq.server;

import com.lwq.common.entity.RpcRequest;
import com.lwq.common.entity.RpcResponse;
import com.lwq.server.register.LocalRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RequestHandlerThread implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandlerThread.class);

    private Socket socket;
    private RequestHandler requestHandler;

    public RequestHandlerThread(Socket socket, RequestHandler requestHandler) {
        this.socket = socket;
        this.requestHandler = requestHandler;
    }

    @Override
    public void run() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject(); // 接收的消费者消息
            String interfaceName = rpcRequest.getInterfaceName(); // 获得接口名
            Object service = LocalRegistry.getService(interfaceName); // 从本地注册中获得该接口对应的服务
            Object result = requestHandler.handle(rpcRequest, service); // 交给requestHandler调用服务
            // TODO:尚不支持返回失败消息
            objectOutputStream.writeObject(RpcResponse.success(result)); // 通过输出流向客户端发送响应消息
            objectOutputStream.flush();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("调用或发送时有错误发生：", e);
        }
    }
}
