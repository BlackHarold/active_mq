package ru.opi.active_mq;

public class TestMain {

    public static void main(String[] args) {
        String body = "{\"id\":\"1234\", \"error\":\"some error\"}";
        System.out.println(body.substring(0, body.indexOf(",")) + "}");
    }
}
