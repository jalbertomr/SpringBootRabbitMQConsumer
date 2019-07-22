package com.bext.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bext.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RabbitMQConsumer {
    Logger logger = LoggerFactory.getLogger(this.getClass());
	//@Autowired
	//private AmqpTemplate rabbitTemplate;

    //@RabbitListener(queues = "${bext.rabbitmq.queueName}")
    @RabbitListener(queues = "bext-queueName")
	public void receivedMessage(Employee employee)  {
		//rabbitTemplate.receiveAndConvert();
		
		logger.info("RabbitListener receivedMessage");
		//String myHeader = (String) message.getMessageProperties().getHeaders().get("mi-header");
		try {
			ObjectMapper mapper = new ObjectMapper();
			String empJson = mapper.writeValueAsString(employee);
			logger.info("Mensage recibido de RabbitMQ: " + empJson);
			//logger.info("RabbitListener receivedMessage" + employee.getEmpName());
		} catch (JsonProcessingException e) {
			logger.error("exception:" + e.getMessage());
		}
	}
    
/*	@RabbitListener(queues = "${bext.rabbitmq.queueName}")
	public void receivedMessage(@Payload Employee employee, @Header("mi-header") String my_header, Message message)  {
		//rabbitTemplate.receiveAndConvert();
		
		logger.info("message-user:" + employee.getEmpName());
		String myHeader = (String) message.getMessageProperties().getHeaders().get("mi-header");
		try {
			ObjectMapper mapper = new ObjectMapper();
			String empJson = mapper.writeValueAsString(employee);
			logger.info("Mensage recibido de RabbitMQ: " + empJson);		
		} catch (JsonProcessingException e) {
			logger.error("exception:" + e.getMessage());
		}
	}
*/	
}
