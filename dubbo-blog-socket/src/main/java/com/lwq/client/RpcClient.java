package com.lwq.client;

import com.lwq.common.entity.RpcRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RpcClient {
    private static final Logger logger = LoggerFactory.getLogger(RpcClient.class);

    public Object sendRequest(RpcRequest rpcRequest, String host, int port) {
        // 1.创建Socket对象并且指定服务器的地址和端口号
        try (Socket socket = new Socket(host, port)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            // 输出流写入发送的消息 RPCRequest
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
            // 输入流接受消息 RPCResponse
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("调用时有错误发生：", e);
            return null;
        }
    }
}
