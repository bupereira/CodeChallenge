package com.wit.springCalculator.rpc;



import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.DeliverCallback;
import com.wit.springCalculator.core.CalculatorCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Component
public class RPCServer {
    private static final String RPC_QUEUE_NAME = "rpc_queue";
    private static final Logger logger = LoggerFactory.getLogger(RPCServer.class);

    @Autowired
    private CalculatorCore calculatorCore;

    private Connection connection = null;
    private boolean on = true;

    public void process() throws TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try {
            connection = factory.newConnection();
            Channel channel = channelSetup(connection);

            DefaultConsumer consumer = new DefaultConsumer(channel);
            logger.info("Now Awaiting RPC requests");
            while(on) {


                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                            .Builder()
                            .correlationId(delivery.getProperties().getCorrelationId())
                            .build();
                    String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                    String response = "";
                    try {

                        response = calculatorCore.run(message);
                    } finally {
                        channel.basicPublish("", delivery.getProperties().getReplyTo(),
                                replyProps, response.getBytes(StandardCharsets.UTF_8));
                        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                    }
                };
                channel.basicConsume(RPC_QUEUE_NAME, false, deliverCallback, (consumerTag -> { }));

            }

        } catch (IOException e) {
            logger.error("IOException encountered in the RPCServer");
            e.printStackTrace();
        } finally {
            turnOff();
        }
    }

    private Channel channelSetup(Connection connection) throws IOException {
        Channel channel = connection.createChannel();
        channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);
        channel.basicQos(1);
        return channel;
    }

    public void turnOff() {
        on = false;
        if(connection != null) {
            try {
                connection.close();
            } catch (IOException e) {
                logger.error("IOException when closing connection on RPCServer");
            }
        }
    }
}
