package ru.opi.active_mq.experimental;

import org.apache.camel.builder.RouteBuilder;

public class SUIDAuth extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:SUIDAuth").routeId("SUIDAuth")
            .routeId("SUIDAuth");
    }
}
