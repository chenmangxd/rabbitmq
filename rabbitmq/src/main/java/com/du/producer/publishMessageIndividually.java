package com.du.producer;

import com.du.utile.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.TimeoutException;

public class publishMessageIndividually {
    private final static String QUEUE_NAME = "hello";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitMqUtils.getchannel();
        channel.confirmSelect();
        Scanner sc=new Scanner(System.in);
        int count=0;
        long begin = System.currentTimeMillis();
        for(int i=0;i<100;i++)
        {
            channel.basicPublish("",QUEUE_NAME,null,"1".getBytes());
            count++;
            if (count==10)
            {
                boolean b = channel.waitForConfirms();
                if (b)
                {
                    System.out.println("消息发布成功");
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end-begin);

    }
}