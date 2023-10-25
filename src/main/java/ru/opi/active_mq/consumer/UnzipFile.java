package ru.opi.active_mq.consumer;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.ZipFileDataFormat;
import org.springframework.stereotype.Component;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.file;
import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.log;

@Component
public class UnzipFile extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        ZipFileDataFormat zipFile = new ZipFileDataFormat();
        zipFile.setUsingIterator("true");

        from(file("input")).routeId("UnzipFile")
                .unmarshal(zipFile)
                .to(log("zip file").showAll(true));
    }
}