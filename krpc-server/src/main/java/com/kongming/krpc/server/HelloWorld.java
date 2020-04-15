package com.kongming.krpc.server;

import com.kongming.krpc.contract.IHelloWorld;

public class HelloWorld implements IHelloWorld {

    @Override
    public String sayHello(String name) {
        return "rpc ok" + name;
    }
}
