package ru.opi.active_mq.controller;

import org.apache.camel.FluentProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.SecureRandom;

@Controller
public class RestController {

    @Autowired
    private JmsTemplate jmsTemplate;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    FluentProducerTemplate template;

    @GetMapping("/fluent")
    public String getProducer() {
        return template.to("direct:SUIDAuth").request(String.class);
    }


    @GetMapping("/message/{msg}")
    public ResponseEntity<String> publishMessage(@PathVariable("msg") String content) {
        jmsTemplate.convertAndSend("local.inmemory.queue", content);
        logger.info("Message published : " + content);

        Message message = new GenericMessage(content);
        return new ResponseEntity(message, HttpStatus.OK);
    }


    @GetMapping("/hello")
    public ResponseEntity<String> getHello(@RequestParam("message") final String text) {
        Message message = new GenericMessage(text);
        jmsTemplate.convertAndSend("spring-boot", message);

        return new ResponseEntity(message, HttpStatus.OK);
    }

    @GetMapping(value = "/example/{id}")
    public ResponseEntity<String> getRandomString(@PathVariable("id") Integer id) {
        String randomString = randomString(id);
        return new ResponseEntity("rest request got random string is " + randomString, HttpStatus.OK);
    }

    String randomString(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));

        return sb.toString();
    }
}
