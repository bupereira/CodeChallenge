package com.wit.springCalculator.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CalculatorServer implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(CalculatorServer.class);

    @Autowired
    private RPCServer rpcServer;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Starting calculator.");
        rpcServer.process();
    }
}
