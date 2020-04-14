package com.kongming.krpc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KrpcProxyServer {
    public void publisher(Object service,int port){
        //线程池
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        //BIO(阻塞IO)
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessHandlerThread(socket,service));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
    }
}
