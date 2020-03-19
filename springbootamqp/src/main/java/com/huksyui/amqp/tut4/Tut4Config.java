package com.huksyui.amqp.tut4;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author huskyui
 * @date 2020/3/18 14:51
 */
@Profile({"tut4","routing"})
@Configuration
public class Tut4Config {
    // 设置exchange
    @Bean
    public DirectExchange direct(){
        return new DirectExchange("tut.direct");
    }

    @Profile("receiver")
    private static class ReceiverConfig{
        // 创建两个匿名Queue  an anonymous, non-durable, exclusive, auto-delete queue
        @Bean
        public Queue autoDeleteQueue1(){
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue2(){
            return new AnonymousQueue();
        }

        // 绑定相关数据
        @Bean
        public Binding binding1a(DirectExchange directExchange,Queue autoDeleteQueue1){
            return BindingBuilder.bind(autoDeleteQueue1).to(directExchange).with("orange");
        }

        @Bean
        public Binding binding1b(DirectExchange directExchange,Queue autoDeleteQueue1){
            return BindingBuilder.bind(autoDeleteQueue1).to(directExchange).with("black");
        }


        @Bean
        public Binding binding2a(DirectExchange directExchange,Queue autoDeleteQueue2){
            return BindingBuilder.bind(autoDeleteQueue2).to(directExchange).with("green");
        }

        @Bean
        public Binding binding2b(DirectExchange direct,Queue autoDeleteQueue2){
            return BindingBuilder.bind(autoDeleteQueue2).to(direct).with("black");
        }

        //
        @Bean
        public Tut4Receiver receiver(){
            return new Tut4Receiver();
        }
    }

    @Profile("sender")
    @Bean
    public Tut4Sender sender(){
        return new Tut4Sender();
    }

}
