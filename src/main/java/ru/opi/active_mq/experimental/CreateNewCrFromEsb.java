package ru.opi.active_mq.experimental;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.ClaimCheckOperation;
import org.apache.camel.model.dataformat.JsonLibrary;

import java.util.Collections;
import java.util.Map;

public class CreateNewCrFromEsb extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:CreateNewCrFromEsb").routeId("CreateNewCrFromEsb")
                .streamCaching()
                .log("[${routeId}] Создаем новый объект")
                .log("[${routeId}] Тело объекта для создания в СУИД:\n${body}")

                .setProperty("fullBody", body())

                .doTry()
                .process(new CreateNewObjectFromEsbProcessor())
                .doCatch(Exception.class)
                .log("[${routeId}] Ошибка заполнения атрибутов тела нового объекта для создания в СУИД.")
                .log("[${routeId}] Exception: ${exception.message}.")
                .log("[${routeId}] DEBUG: STOP EXECUTING.")
                //.setProperty("createNewObjectError", simple("${exception.message}"))
                .setBody(exchange -> {
                    Map<String, Object> fullBody = exchange.getProperty("fullBody", Map.class);
                    fullBody.put("transactionState",
                            Collections.singletonMap("1502", exchange.getIn().getBody(String.class)));
                    log.info("fullBody " + fullBody);
                    return fullBody;
                })
                .to("activemq:SendReceipt")
                .process(new CleanAllProcessor())
                .stop()
                .end()

                .marshal().json(JsonLibrary.Jackson)
                .log("[${routeId}] Сформированное тело нового объекта для создания в СУИД:\n${body}")

                .setProperty("suidCookie", simple("${exchangeProperty.fullBody[suidCookie]}"))
                .claimCheck(ClaimCheckOperation.Push)
                .to("direct:SUIDAuth")
                .claimCheck(ClaimCheckOperation.Pop)
                .removeHeaders("*")
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .setHeader("Cookie", simple("${exchangeProperty.suidCookie}"))

                .toD("https4://{{suid.server}}/3dspace/remote/businessobject?httpMethod=POST" +
                        "&throwExceptionOnFailure=false&bridgeEndpoint=true&connectionClose=true")

                .choice()
                .when(method("methodBeans", "httpAnswerNoSuccess"))
                .log("[${routeId}] Ошибка создания объекта в СУИД.\nResponse:\n${body}\nHeaders:\n${headers}")
                .setBody(exchange -> {
                    Map<String, Object> fullBody = exchange.getProperty("fullBody", Map.class);
                    fullBody.put("transactionState",
                            Collections.singletonMap("1502", exchange.getIn().getBody(String.class)));
                    return fullBody;
                })
                .toD("activemq:SendReceipt")
                .process(new CleanAllProcessor())
                .stop()
                .end()

                .unmarshal().json(JsonLibrary.Jackson)

                .log("[${routeId}] Объект ${exchangeProperty.fullBody[objectType]} успешно создан в СУИД}")
                .log("[${routeId}] Ответ от СУИД:\n${body}")
                .setBody(exchange -> {
                    Map<String, String> response = exchange.getIn().getBody(Map.class);
                    Map<String, Object> fullBody = exchange.getProperty("fullBody", Map.class);
                    fullBody.put("suidId", response.get("id"));
                    fullBody.put("objectStatus", "created");
                    return fullBody;
                })
                .removeHeaders("*");

                //.process(new CheckAttachmentProcessor())

                //.choice()
                //.when(simple("${property.attachmentIsValid} == true"))
                //.log("[${routeId}] Attachment is valid. Send files to SUID")
                //.to("direct:FilesToSUID")
                //.otherwise()
                //.log("[${routeId}] Attachment is invalid. Send files to SUID canceled.")
                //.setBody(exchange -> {
                //    Map<String, Object> fullBody = exchange.getProperty("fullBody", Map.class);
                //    fullBody.put("transactionState",
                //            Collections.singletonMap("1502", "Attachment is invalid. Send files to SUID canceled."));
                //    return fullBody;
                //})
                //.to("activemq:SendReceipt")
                //.process(new CleanAllProcessor())
                //.stop()
                //.end()

                //.setBody(simple("${exchangeProperty.fullBody}"))

                //.to("direct:CommentHandler")

                //.setBody(exchange -> {
                //    Map<String, Object> fullBody = exchange.getProperty("fullBody", Map.class);
                //    fullBody.putIfAbsent("transactionState",
                //            Collections.singletonMap("1204", "Create new object successful."));
                //    return fullBody;
                //})
                //.to("activemq:SendReceipt")
                //.process(new CleanAllProcessor());

    }
}
