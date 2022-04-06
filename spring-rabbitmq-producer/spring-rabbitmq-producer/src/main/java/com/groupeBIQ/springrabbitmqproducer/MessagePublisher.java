package com.groupeBIQ.springrabbitmqproducer;

import java.util.Date;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagePublisher {

	@Autowired
	private RabbitTemplate template;
	
	@PostMapping("/publish")
	public String publishMessage(@RequestBody CustomMessage message) {
		message.setMessage(UUID.randomUUID().toString());
		message.setMessageDate(new Date());
		template.convertAndSend(MQConfig.Exchange, MQConfig.ROUTING_KEY,message);
		return "MESSAGE PUBLISHED";
	}
	
	
}
