package ru.opi.active_mq.consumer;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqReceiverRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:demo-active-queue").routeId("demo-active-queue")
                .to("log:received-message-from-active-mq");
    }
}
