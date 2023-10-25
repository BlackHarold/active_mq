package ru.opi.active_mq.consumer;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.*;

//@Component
public class ActiveMqIntegrationSelector extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from(activemq("integration")
                .selector("transactionId=4b336db2-2869-4f15-9e95-fdd4bb536ff5"))
                .routeId("consumer")
                .to(log("integration message"));
    }
}
