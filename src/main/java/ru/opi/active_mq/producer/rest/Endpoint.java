package ru.opi.active_mq.producer.rest;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class Endpoint extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        rest("/fco").get("/hello").routeId("rest-fco")
                .produces(MediaType.TEXT_PLAIN_VALUE)
                .outType(String.class)
                .param()
                .name("name")
                .type(RestParamType.query)
                .endParam()
                .to("direct:helloFCO");

        rest("/fcr").get("/hello").routeId("rest-fcr")
                .produces(MediaType.TEXT_PLAIN_VALUE)
                .outType(String.class)
                .param()
                .name("name")
                .type(RestParamType.query)
                .endParam()
                .to("direct:helloFCR");

        rest("/go").get("/auth")
                .to("direct:SuidAuthRoute");

        rest("/active").get()
                .to("direct:active-mq-sender-router");

        from("direct:helloFCO")
                .setBody(simple("FCO: Hello, ${header.name}"));

        from("direct:helloFCR")
                .setBody(simple("FCR: Hello, ${header.name}"));
    }
}