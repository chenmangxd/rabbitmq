package com.du.consumer;

import com.du.utile.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

public class ReceiveLogs02 {
    private static final String EXCHANGE_NAME = "logs";
    public static void main(String[] args) throws  Exception{
        Channel channel = RabbitMqUtils.getchannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queuename = channel.queueDeclare().getQueue();
        channel.queueBind(queuename,EXCHANGE_NAME,"");
        System.out.println("等待接受消息");
        DeliverCallback deliverCallback = (consumerTag, delivery) ->
        {String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("控制台打印接收到的消息"+message);
        };
        channel.basicConsume(queuename, true, deliverCallback, consumerTag -> { });
    }
    }
