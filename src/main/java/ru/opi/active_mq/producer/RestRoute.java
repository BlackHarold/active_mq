package ru.opi.active_mq.producer;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class RestRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().host("localhost").port("{{server.port}}");

        from("timer:hello?period={{long.timer.period}}")
                .setHeader("id", simple("${random(6,9)}"))
                .to("rest:get:example/{id}")
                .log("${body}");
    }
}
