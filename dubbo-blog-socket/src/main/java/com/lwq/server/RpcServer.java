package com.lwq.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class RpcServer {
    /**
     * 服务端使用一个ServerSocket监听某个端口，循环接收连接请求，如果发来了请求就创建一个线程，在新线程中处理调用。这里创建线程采用线程池.
     */

    private static final Logger logger = LoggerFactory.getLogger(RpcServer.class);

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAXIMUM_POOL_SIZE = 50;
    private static final int KEEP_ALIVE_TIME = 60;
    private static final int BLOCKING_QUEUE_CAPACITY = 100;
    private final ExecutorService threadPool;
    private RequestHandler requestHandler = new RequestHandler();

    public RpcServer() {
        BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<>(BLOCKING_QUEUE_CAPACITY);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, workingQueue, threadFactory);
    }

    /**
     * 启动服务器
     *
     * @param port
     */
    public void start(int port) {
        // 1.创建ServerSocket对象并且绑定一个端口port
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("服务器启动……");
            Socket socket;
            // 2.通过accept()监听客户端请求
            while ((socket = serverSocket.accept()) != null) {
                logger.info("消费者连接: {}:{}", socket.getInetAddress(), socket.getPort());
                // 3.在线程池中取出一个线程，执行线程，完成服务调用
                threadPool.execute(new RequestHandlerThread(socket, requestHandler));
            }
            threadPool.shutdown();
        } catch (IOException e) {
            logger.error("服务器启动时有错误发生:", e);
        }
    }

}
