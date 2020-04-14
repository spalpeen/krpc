package com.kongming.krpc.server;

import com.kongming.krpc.contract.IHelloWorld;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class KrpcApplication {

	public static void main(String[] args) {
		IHelloWorld helloWorld = new HelloWorld();
		KrpcProxyServer krpcProxyServer = new KrpcProxyServer();
		krpcProxyServer.publisher(helloWorld,8080);
		SpringApplication.run(KrpcApplication.class, args);
	}


}
