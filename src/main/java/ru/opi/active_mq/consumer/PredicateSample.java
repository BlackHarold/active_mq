package ru.opi.active_mq.consumer;

import org.apache.camel.Predicate;
import org.apache.camel.builder.PredicateBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.log;
import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.timer;

//@Component
public class PredicateSample extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from(timer("timer").period(1000))
                .to(log("log").showBody(true))
                .process(exchange -> {
                    Predicate bodyNotNullOrEmpty = PredicateBuilder.and(
                            body().isEqualTo(null),
                            PredicateBuilder.or(
                                    body().isNotEqualTo(null),
                                    body().isNotEqualTo(null))
                    );

                    boolean result = bodyNotNullOrEmpty.matches(exchange);
                    exchange.getMessage().setBody(result);
                })
                .log("${body}");
    }
}
