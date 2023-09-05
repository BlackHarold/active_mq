package ru.opi.active_mq.producer.rest;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

@Component
public class Endpoint extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        rest("/fco").get("/hello")
                .produces("text/plain")
                .outType(String.class)
                .param()
                .name("name")
                .type(RestParamType.query)
                .endParam()
                .to("direct:helloFCO");

        rest("/fcr").get("/hello")
                .produces("text/plain")
                .outType(String.class)
                .param()
                .name("name")
                .type(RestParamType.query)
                .endParam()
                .to("direct:helloFCR");

        from("direct:helloFCO")
                .setBody(simple("FCO: Hello, ${header.name}"));

        from("direct:helloFCR")
                .setBody(simple("FCR: Hello, ${header.name}"));
    }
}