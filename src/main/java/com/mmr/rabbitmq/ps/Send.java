package com.mmr.rabbitmq.ps;

import com.mmr.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author huskyui
 * @date 2020/3/1 17:36
 */

public class Send {
    private final static String EXCHANGE_NAME = "test_exchange_fanout";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection() ;
        Channel channel = connection.createChannel();
        // 声明队列
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);//分发类型fanout

        String msg = "hello world ps";
        // 发送信息
        channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes());
        System.out.println("send success");
        // 关闭流
        channel.close();
        connection.close();
    }
}
