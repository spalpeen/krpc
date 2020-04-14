package com.kongming.krpc.server;

import com.kongming.krpc.contract.KrpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProcessHandlerThread implements Runnable {
    Socket socket;

    Object service;

    public ProcessHandlerThread(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {
            KrpcRequest krpcRequest = (KrpcRequest) inputStream.readObject();
            Object result = invoke(krpcRequest);
            outputStream.writeObject(result);
            outputStream.flush();
        } catch (Exception e) {

        }
    }

    private Object invoke(KrpcRequest krpcRequest) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = Class.forName(krpcRequest.getClassName());
        Method method = clazz.getMethod(krpcRequest.getMethodName(), krpcRequest.getTypes());
        return method.invoke(service, krpcRequest.getParams());
    }
}
