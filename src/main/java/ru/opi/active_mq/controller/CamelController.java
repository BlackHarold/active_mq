package ru.opi.active_mq.controller;

import jakarta.ws.rs.core.MediaType;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.builder.ValidatorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.*;

@Component
public class CamelController extends RouteBuilder {

    @Autowired
    private Environment env;

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .contextPath("/rest/api/*")
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "REST API for processing Order")
                .apiProperty("api.version", "1.0")
                .apiContextRouteId("doc-api")
                .bindingMode(RestBindingMode.json);

        rest("/order/").get("/process").description("Process order")
                .to("log: fetchProcess");


        rest("tmp").get()
                .to(log("tmp log").showAll(true).getUri())
                .to(mock("result").getUri());
//                .to(validator().withUri("uri"));
//                .to(mock("result").getUri());

        rest("mq").post()
                .tag("active_mq")
                .consumes(MediaType.APPLICATION_JSON)
                .produces(MediaType.APPLICATION_JSON)
                .to(direct("ActiveMQ").toString());
    }
}
