package com.du.producer;

import com.du.utile.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class producer1 {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtils.getchannel();
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        Scanner sc=new Scanner(System.in);
        while (true)
        {
            channel.basicPublish("",QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,sc.next().getBytes());
            System.out.println("发送了一条消息");
        }
    }
}