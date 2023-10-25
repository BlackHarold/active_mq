package ru.opi.active_mq.validator;

import org.apache.camel.Message;
import org.apache.camel.ValidationException;
import org.apache.camel.spi.DataType;
import org.springframework.stereotype.Component;

@Component
public class Validator extends org.apache.camel.spi.Validator {
    @Override
    public void validate(Message message, DataType type) throws ValidationException {
        System.out.println("validate");
    }
}
