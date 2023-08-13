package ru.opi.active_mq.constants;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public final class HeadersData {
    public static final String IMS3 = "IMS3";
    public static final String EDB = "EDB";
    public static final String adapterName = "transactionSuidEdbFCR";
    public static final String adapterVersion = "1.0.0";
    public static final String BASIC_ESB = "Basic c3VpZEZjclRlc3Q6YzQ1NGNKd2pEQmtnNFFQNQ==";
    public static final Set<Integer> HTTP_SUCCESS_CODES = new HashSet<>(Arrays.asList(200, 201, 202, 203, 204, 205, 206));
}
