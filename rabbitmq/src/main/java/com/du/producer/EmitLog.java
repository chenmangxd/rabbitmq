package com.du.producer;

import com.du.utile.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.lang.reflect.Member;
import java.util.Scanner;

public class EmitLog {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args)  throws Exception{
        Channel channel = RabbitMqUtils.getchannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        Scanner sc=new Scanner(System.in);
        while (true)
        {
            channel.basicPublish(EXCHANGE_NAME,"",null, sc.next().getBytes());
            System.out.println("发出了消息");
        }
    }
}