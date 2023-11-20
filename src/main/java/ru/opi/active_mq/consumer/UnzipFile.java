package ru.opi.active_mq.consumer;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.ZipFileDataFormat;
import org.springframework.stereotype.Component;

import java.util.Iterator;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.*;

@Component
public class UnzipFile extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        ZipFileDataFormat zipFile = new ZipFileDataFormat();
        zipFile.setUsingIterator("true");

        from(file("input")).routeId("UnzipFile")
                .unmarshal(zipFile)
                .split(bodyAs(Iterator.class)).streaming()
                .log("zipFileName ${headers.zipFileName}")
                .to(mock("result"));
    }
}
