package com.mmr.rabbitmq.tx;

import com.mmr.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 事务相关，可以判断信息确定发送到RabbitMQServer
 *
 * @author huskyui
 * @date 2020/5/6 22:11
 */

public class TxSend {
    private static final String QUEUE_NAME = "test_queue_tx";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String msg = "hello tx message";
        try {
            channel.txSelect();
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            // 下面放开
//            System.out.println(1/0);
            channel.txCommit();
        }catch (Exception e){
            e.printStackTrace();
            channel.txRollback();
            System.out.println("send message txRollback");
        }
        channel.close();
        connection.close();

    }
}
