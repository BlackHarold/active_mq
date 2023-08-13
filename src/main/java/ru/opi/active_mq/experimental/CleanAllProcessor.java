package ru.opi.active_mq.experimental;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class CleanAllProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.removeProperties("*");
        exchange.getIn().removeHeaders("*");
        exchange.getIn().setBody(null);
    }
}
