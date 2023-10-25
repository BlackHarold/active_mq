package ru.opi.active_mq.consumer;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.*;


@Component
public class ActiveMQSenderRouter extends RouteBuilder {
    String uid = UUID.randomUUID().toString();

    @Override
    public void configure() throws Exception {
        from(direct("ActiveMQ")).routeId("ActiveMQ")
                .to(log("-> queue"))
                .setHeader("transactionId", constant(uid))
                .to(activemq("integration")
                        .deliveryPersistent(false)
                        .disableReplyTo(true));
    }
}
