package ru.opi.active_mq.experimental;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.opi.active_mq.constants.Headers;
import ru.opi.active_mq.constants.HeadersData;

import java.util.HashMap;
import java.util.Map;

@Component
public class SendReceipt extends RouteBuilder {

    @Autowired
    Headers headers;
    HeadersData headersData;

    @Override
    public void configure() throws Exception {
        from("activemq:SendReceipt").routeId("SendReceipt")
                .streamCaching()
                .setProperty("fullBody", body())

                .removeHeaders("*")
                .setHeader(headers.TARGET_SYSTEM_CODE, constant(headersData.IMS3))
                .setHeader(headers.TARGET_PROJECT_CODE, constant(headersData.EDB))
                .setHeader(headers.ADAPTER_NAME, constant(headersData.adapterName))
                .setHeader(headers.ADAPTER_VERSION, constant(headersData.adapterVersion))
                .setHeader(headers.TRANSACTION_ID, simple("${exchangeProperty.fullBody[transactionId]}"))
                .setHeader(headers.AUTHORIZATION, constant(headersData.BASIC_ESB))
                .setBody(exchange -> {
                    Map<String, Object> fullBody = exchange.getProperty("fullBody", Map.class);
                    Map<String, Object> status = new HashMap<>();
                    Map<String, String> transactionState = (Map<String, String>) fullBody.get("transactionState");
                    if (transactionState.containsKey("1204")) {
                        status.put("transactionState", "Success");
                        status.put("code", 1204);
                        status.put("codeTxt", "transactionSuccess");
                        status.put("message", transactionState.get("1204"));
                    } else if (transactionState.containsKey("1502")) {
                        status.put("transactionState", "Fail");
                        status.put("code", 1502);
                        status.put("codeTxt", "dataOutSendFail");
                        status.put("message", transactionState.get("1502"));
                    }
                    return status;
                })

                .marshal().json(JsonLibrary.Jackson)
                .log("[${routeId}] TO_TRANSACTION_ESB: ${body}")
                .toD("{{esb.protocol}}://{{esb.server}}/camel-spring/camelRestApi/" +
                        "{{esb.service.chm}}/${exchangeProperty.fullBody[objectReceipt]}?httpMethod=POST" +
                        "&throwExceptionOnFailure=false&bridgeEndpoint=true&connectionClose=true")
                .choice()
                .when(method("methodBeans", "httpAnswerSuccess"))
                .log("[${routeId}] Квитанция успешно отправлена в шину")
                .otherwise()
                .log("[${routeId}] Ошибка отправки квитанции в шину.\n" +
                        "Body:\n${body}\nHeaders:\n${headers}")
                .end();
    }
}
