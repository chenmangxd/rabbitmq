package com.du.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class consumer {
    private final static String QUEUE_NAME = "hello";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.79.128");
        factory.setUsername("admin");
        factory.setPassword("123");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        System.out.println("等待接受消息");
        DeliverCallback deliverCallback=(consumerTag,delivery)->{String message= new String(delivery.getBody());
        System.out.println(message);};
        CancelCallback cancelCallback=(consumerTag)->{System.out.println("消息消费被中断");};
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
    }
}