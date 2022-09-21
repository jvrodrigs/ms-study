package com.ms.mscreditappraiser.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.create-cards}")
    private String nameQueue;

    @Bean
    public Queue queueIssueCards(){
        return new Queue(nameQueue, true);
    }
}
