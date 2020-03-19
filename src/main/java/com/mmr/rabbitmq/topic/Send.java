package com.mmr.rabbitmq.topic;

import com.mmr.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author huskyui
 * @date 2020/3/11 15:34
 */

public class Send {
    private final static String EXCHANGE_NAME = "test_exchange_topic";
    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        //声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        // 消息内容
        String msg = "hello world!";
        channel.basicPublish(EXCHANGE_NAME,"routekey1.1",null,msg.getBytes());
        channel.close();
        connection.close();

    }

}
