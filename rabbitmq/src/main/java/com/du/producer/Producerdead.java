package com.du.producer;

import com.du.utile.RabbitMqUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

public class Producerdead {
    private static final String NORMAL_EXCHANGE = "normal_exchange";
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getchannel();
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().expiration("10000").build();
        for(int i=0;i<10;i++)
        {
            String message="info"+i;
            channel.basicPublish(NORMAL_EXCHANGE,"zhangsan",properties,message.getBytes());
            System.out.println("生产者发送消息:"+message);
        }

    }
}