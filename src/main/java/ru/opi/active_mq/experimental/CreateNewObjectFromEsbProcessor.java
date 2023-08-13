package ru.opi.active_mq.experimental;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.HashMap;
import java.util.Map;

public class CreateNewObjectFromEsbProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        //Fcr fcrEsb = new FcrFromEsb(); - convert from exchange to FcrFromEsb to CrToSuid
        Map<String, Object> newObject = new HashMap<>();
        Map<String, String> connect = new HashMap<>();
        Map<String, Object> fullBody = exchange.getIn().getBody(Map.class);
        Map<String, Object> documentSet = (Map<String, Object>) fullBody.get("documentSet");

        newObject.put("type", "edsChangeRequest");
        newObject.put("policy", "edsChangeRequest");
        newObject.put("name", fullBody.get("name").toString());
        newObject.put("edsIMSObjectID", fullBody.get("imsId").toString());

        if (!connect.isEmpty()) {
            connect.put("fromId", documentSet.get("suidId").toString());
            newObject.put("connect", connect);
        }

        //print newObject :)
        exchange.getIn().setBody(newObject);
    }
}
