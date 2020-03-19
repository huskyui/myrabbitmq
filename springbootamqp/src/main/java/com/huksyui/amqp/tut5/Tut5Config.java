package com.huksyui.amqp.tut5;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author huskyui
 * @date 2020/3/18 17:43
 */
@Profile({"tut5","topics"})
@Configuration
public class Tut5Config {

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("tut.topic");
    }

    @Profile("receiver")
    private static class ReceiverConfig{
        @Bean
        public Tut5Receiver receiver(){
            return new Tut5Receiver();
        }

        @Bean
        public Queue autoDeleteQueue1(){
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue2(){
            return new AnonymousQueue();
        }

        @Bean
        public Binding binding1a(TopicExchange topicExchange,Queue autoDeleteQueue1){
            return BindingBuilder.bind(autoDeleteQueue1).to(topicExchange).with("*.orange.*");
        }

        @Bean
        public Binding binding1b(TopicExchange topicExchange,Queue autoDeleteQueue1){
            return BindingBuilder.bind(autoDeleteQueue1).to(topicExchange).with("*.*.rabbit");
        }

        @Bean
        public Binding binding2a(TopicExchange topic,Queue autoDeleteQueue2){
            return BindingBuilder.bind(autoDeleteQueue2).to(topic).with("lazy.#");
        }

    }

    @Profile("sender")
    @Bean
    public Tut5Sender sender(){
        return new Tut5Sender();
    }


}
