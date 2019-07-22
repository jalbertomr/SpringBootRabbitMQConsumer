package com.bext.service.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiMessagePostProcessor implements MessagePostProcessor {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${bext.rabbitmq.mi-header}")
	private String header ="miheader inicializado en funcion";
	@Value("${bext.rabbitmq.delay}")
	private Integer delay= 50000;
	
	@Override
	public Message postProcessMessage(Message message) throws AmqpException {
		logger.info("MiMessagePostPocessor parametros:" + header + " " + delay);
		message.getMessageProperties().setHeader("mi-header", header);
		message.getMessageProperties().setDelay(delay);
		return message;
	}

}
