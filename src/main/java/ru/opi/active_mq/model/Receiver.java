package ru.opi.active_mq.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

import java.util.concurrent.CountDownLatch;

//@Component
public class Receiver {

    private final Logger LOG = LoggerFactory.getLogger(Receiver.class);
    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    @JmsListener(destination = "spring-boot")
    public void getSpringBootQueue(String message) {
        LOG.info("spring-boot said [{}]", message);
    }

    @JmsListener(destination = "suid-integration")
    public void getSuidIntegrationQueue(String message) {
        LOG.info("suid-integration said [{}]", message);
    }
}
