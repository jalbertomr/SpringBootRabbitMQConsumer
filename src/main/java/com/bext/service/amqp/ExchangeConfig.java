package com.bext.service.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class ExchangeConfig {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${bext.rabbitmq.queueName}")
	String queueName = "bext-queueName";;
	@Value("${bext.rabbitmq.exchange}")
	String exchange = "bext-exchange";
	@Value("${bext.rabbitmq.routingkey}")
	private String routingkey = "bext-routingkey";
	
	//En sender tenemos
/*	
	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}
*/	
	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}
/*	
	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingkey);
	}
*/	
	
/*	@Bean
	public MessageConverter jsonMessageConverter() {
	   return new Jackson2JsonMessageConverter();	
	}
	
	@Bean
	public AmqpTemplate rabbitTemplate( ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
*/
	
	// codigo original
/*
    @Bean
    TopicExchange miTopicExchange(AmqpAdmin amqpAdmin){
        TopicExchange topicExchange = new TopicExchange("bext-exchange",true,true);
        amqpAdmin.declareExchange(topicExchange);
        return  topicExchange;
    }
*/
    @Bean
    Queue userQueue(AmqpAdmin amqpAdmin){
        Queue queue = new Queue("bext-queueName",true);
        logger.info( "userQueue: "+ queue.toString());
        amqpAdmin.declareQueue(queue);
        return  queue;
    }

    @Bean
    Binding userQueueBindingToOrder(AmqpAdmin amqpAdmin,Queue userQueue,DirectExchange exchange/*TopicExchange johnTopicExchange*/) {
        Binding binding = BindingBuilder.bind(userQueue).to(exchange/*miTopicExchange*/).
                with("bext-routingkey");  //user.service
        amqpAdmin.declareBinding(binding);
        return binding;
    }
    @Bean
    Binding userQueueBindingToSeq(AmqpAdmin amqpAdmin,Queue userQueue,DirectExchange exchange/*TopicExchange johnTopicExchange*/){
        Binding binding = BindingBuilder.bind(userQueue).to(exchange/*miTopicExchange*/).
                with("bext-routingkey");  //seq.service
        amqpAdmin.declareBinding(binding);
        return binding;
    }

	
}
