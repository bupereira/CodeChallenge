package com.wit.rest.client;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.RpcClient;
import com.rabbitmq.client.RpcClientParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RPCClient {
    private static final Logger logger = LoggerFactory.getLogger(RPCClient.class);

    private static ConnectionFactory factory = new ConnectionFactory();

    private Connection connection;
    private Channel channel;
    private String replyQueueName;
    private DefaultConsumer consumer;

    public RPCClient() throws Exception {
        factory.setHost("localhost");
        factory.setPort(AMQP.PROTOCOL.PORT);
        connection = factory.newConnection();
        channel = connection.createChannel();
        replyQueueName = channel.queueDeclare().getQueue();
        consumer = new DefaultConsumer(channel) ;
        channel.basicConsume(replyQueueName, true, consumer);
    }

    public String call(String message) {

        try {
            String correlationId = MDC.get("UNIQUE_ID");
            if(correlationId == null) {
                correlationId = UUID.randomUUID().toString();
            }
            logger.info("ID assigned to client: " + correlationId + ". Attached message: " + message);
            connection = factory.newConnection();
            channel = connection.createChannel();
            String finalCorrelationId = correlationId;
            RpcClientParams rpcClientParams = new RpcClientParams()
                    .channel(channel)
                    .correlationIdSupplier(() -> finalCorrelationId)
                    .exchange("")
                    .routingKey("rpc_queue");
            RpcClient service = new RpcClient(rpcClientParams);
            String response = service.stringCall(message) + "!!" + correlationId; // append correlationId
            connection.close();
            return response;
        } catch (Exception e) {
            System.err.println("Main thread caught exception: " + e);
            e.printStackTrace();
            System.exit(1);
        }
        return null; // should never happen

    }

}
