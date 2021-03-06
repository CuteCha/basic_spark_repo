package com.cxq.httpServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * Created by cxq on 2019-06-21 09:40
 */
public class SimpleServer {
    public static void main(String[] arg) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(10001), 0);
        server.createContext("/test", new TestHandler());
        server.start();
        System.out.println("running......");
    }

    static class TestHandler implements HttpHandler{
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "hello world";
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
