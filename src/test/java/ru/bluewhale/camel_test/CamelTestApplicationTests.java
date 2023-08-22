package ru.bluewhale.camel_test;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CamelTestApplicationTests {

    @Autowired
    private ProducerTemplate template;

    @EndpointInject("mock:result-body")
    private MockEndpoint mock;

    @Test
    void whenSendBodyWithBaeldung_thenGoodbyeMessageReceivedSuccessfully() throws InterruptedException {
        mock.expectedBodiesReceived("Goodbye, Baeldung!");
        System.out.println("Goodbye, Baeldung!");

        template.sendBody("direct:start-conditional", "Hello Baeldung Readers!");

        System.out.println("mock " + mock);
        mock.assertIsSatisfied();
    }

}
