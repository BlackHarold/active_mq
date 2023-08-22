package ru.opi.active_mq.producer;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqSenderRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:active-mq-sender-router").routeId("ActiveMqSenderRouter")
//        from("timer:active-mq-time?period=10000")
                .transform().constant("constant message")
                .to("log:ActiveMqSenderRouter")
//                .log("${body}")
                .to("activemq:demo-active-queue");
    }
}
