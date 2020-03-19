package com.mmr.rabbitmq.topic;

import com.mmr.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author huskyui
 * @date 2020/3/11 15:41
 */

public class Rece1 {
    private final static String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        //
        String queueName = channel.queueDeclare().getQueue();
//        // 声明队列
//        channel.queueDeclare(queueName,false,false,false,null);


        //绑定队列到交换机上
        channel.queueBind(queueName,EXCHANGE_NAME,"routekey.*");

        //同一时刻服务器只发送一条消息
        channel.basicQos(1);

        Consumer consumer  = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("rece1 :" + msg);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("rece1 done");
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };
        channel.basicConsume(queueName,false,consumer);
    }
}
