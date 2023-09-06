package ru.opi.active_mq.producer;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class HttpRequestCamel extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:SuidAuthRoute").routeId("SuidAuthRoute")
//                .setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.component.http.HttpMethods.POST))
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .to("https://www.google.com/?bridgeEndpoint=true")
                .to("log:response");
    }
}
