package com.du.producer;

import com.du.utile.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.util.HashMap;
import java.util.Map;

public class EmitLogTopic {
    private static final String EXCHANGE_NAME = "topic_logs";
    public static void main(String[] args) throws  Exception{
        Channel channel = RabbitMqUtils.getchannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");
        Map<String, String> bindingKeyMap = new HashMap<>();
        bindingKeyMap.put("quick.orange.rabbit","被队列 Q1Q2 接收到");
        bindingKeyMap.put("lazy.orange.elephant","被队列 Q1Q2 接收到");
        bindingKeyMap.put("quick.orange.fox","被队列 Q1 接收到");
        bindingKeyMap.put("lazy.brown.fox","被队列 Q2 接收到");
        bindingKeyMap.put("lazy.pink.rabbit","虽然满足两个绑定但只被队列 Q2 接收一次");
        bindingKeyMap.put("quick.brown.fox","不匹配任何绑定不会被任何队列接收到会被丢弃");
        bindingKeyMap.put("quick.orange.male.rabbit","是四个单词不匹配任何绑定会被丢弃");
        bindingKeyMap.put("lazy.orange.male.rabbit","是四个单词但匹配 Q2");
        for (Map.Entry< String, String > bindingKeyEntry : bindingKeyMap.entrySet()) {
            String key = bindingKeyEntry.getKey();
            String value = bindingKeyEntry.getValue();
            channel.basicPublish(EXCHANGE_NAME,key,null,value.getBytes());
            System.out.println("生产者发出消息" + value);
        }
    }
}