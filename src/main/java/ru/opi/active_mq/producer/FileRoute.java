package ru.opi.active_mq.producer;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import ru.opi.active_mq.filter.FileFilter;

@Component
public class FileRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
//        from("file:files/input?noop=true")
        from("file:files/input")
                .log("${body}")
                .filter().method(FileFilter.class)
                .to("file:files/output");
    }
}
