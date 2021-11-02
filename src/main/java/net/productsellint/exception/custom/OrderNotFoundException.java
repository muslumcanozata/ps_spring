package net.productsellint.exception.custom;

import net.productsellint.exception.base.ValidationException;

import java.text.MessageFormat;

public class OrderNotFoundException extends ValidationException {
    private static final String MESSAGE ="Girilen id ile ilişkili bir sipariş bulunamadı. Id: {0}";

    public OrderNotFoundException(Integer id) {
        super(MessageFormat.format(MESSAGE, id));
    }
}
