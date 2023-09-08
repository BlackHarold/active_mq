package ru.opi.active_mq.experimental;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SUIDAuth extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:SUIDAuth").routeId("SUIDAuth")
                .process(exchange -> {
                    exchange.getIn().setBody("some answer");
                });
    }
}
