package com.lwq.framework.protocol.http;

import com.lwq.framework.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpClient {
    public String send(String hostname, Integer port, Invocation invocation) {
        // 用户配置
        // 客户端发送请求，接受结果
        String result = null;
        try {
            URL url = new URL("http", hostname, port, "/");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);

            oos.writeObject(invocation);
            oos.flush();
            oos.close();

            InputStream inputStream = httpURLConnection.getInputStream();

            result = IOUtils.toString(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }
}
