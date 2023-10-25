package ru.opi.active_mq.producer;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.minio;
import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.timer;

//@Component
public class TimerRoute extends RouteBuilder {

//    @Autowired
    LoggingProcessingComponent logProcess;

    @Override
    public void configure() throws Exception {
        from(minio("").delay("t1")/*"timer:t1?period=10000"*/.deleteAfterRead(false)).routeId("")
                .bean("getCurrentTimeBean")
                .log("after current time bean ${body}")
                .bean(logProcess)
                .log("after logging process ${body}")
                .process(new ExampleProcessor())
                .log("${body}");

//        from(timer("t2").period(60000))
//            .to("direct:active-mq-sender-router");
    }
}

@Component
class GetCurrentTimeBean {
    String getCurrentTime() {
        return ("time is: " + LocalDateTime.now());
    }
}

@Component
class LoggingProcessingComponent {
    private Logger log = LoggerFactory.getLogger(LoggingProcessingComponent.class);

    public void process(String message) {
        log.info("in logging process {}", message);
    }

}

class ExampleProcessor implements Processor {

    private Logger log = LoggerFactory.getLogger(ExampleProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        log.info("example process exchange: {} get message: {}", exchange, exchange.getMessage());
    }
}
