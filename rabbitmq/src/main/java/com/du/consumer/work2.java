package com.du.consumer;

import com.du.utile.RabbitMqUtils;
import com.du.utile.SleepUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class work2{
    private final static String QUEUE_NAME = "2a9bfafa-369a-46e2-aef7-6170b038da65";
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtils.getchannel();
        channel.basicQos(2);
        DeliverCallback deliverCallback=(consumerTag,delivery)->{
            String receivedMessage = new String(delivery.getBody());
//            SleepUtils.sleep(30);
            System.out.println("接收到消息:"+receivedMessage);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };
        CancelCallback cancelCallback=(consumerTag)->{
            System.out.println(consumerTag+"消费者取消消费接口回调逻辑");};
        System.out.println("C2 消费者启动等待消费.................. ");
        channel.basicConsume(QUEUE_NAME,false,deliverCallback,cancelCallback);
    }
}

