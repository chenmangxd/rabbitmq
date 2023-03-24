package com.du.consumer;

import com.du.utile.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

public class ReceiveLogsTopic02 {
    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getchannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");
        String queuename="Q2";
        channel.queueDeclare(queuename,false,false,false,null);
        channel.queueBind(queuename, EXCHANGE_NAME, "*.*.rabbit");
        channel.queueBind(queuename, EXCHANGE_NAME, "lazy.#");
        System.out.println("等待接收消息........... ");
        DeliverCallback deliverCallback= (consumerTag, delivery) ->{
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" 接 收 队 列 :"+queuename+"绑定键:"+delivery.getEnvelope().getRoutingKey()+",消息:"+message);
        };
        channel.basicConsume(queuename, true, deliverCallback, consumerTag -> {
        });
    }
}