package net.productsellint.exception.custom;

import net.productsellint.exception.base.ValidationException;

import java.text.MessageFormat;

public class EntityStatusNotFoundException extends ValidationException {

    private static final String MESSAGE = "Girilen {0} ile ilişkili bir status bulunamadı. Value: {1}";

    public EntityStatusNotFoundException(Integer value) {
        super(MessageFormat.format(MESSAGE, "value", value));
    }
}

