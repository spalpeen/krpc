package com.kongming.krpc.client;

import com.kongming.krpc.contract.IHelloWorld;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class KrpcClientApplication {

	public static void main(String[] args) {
		//SpringApplication.run(KrpcClientApplication.class, args);
		KrpcClientProxy krpcClientProxy = new KrpcClientProxy();
		IHelloWorld iHelloWorld = krpcClientProxy.clientProxy(IHelloWorld.class,"localhost",8080);
		String rs = iHelloWorld.sayHello("1");
		System.out.println(rs);
	}


}
