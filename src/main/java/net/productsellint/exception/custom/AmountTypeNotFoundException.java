package net.productsellint.exception.custom;

import net.productsellint.exception.base.ValidationException;

import java.text.MessageFormat;

public class AmountTypeNotFoundException extends ValidationException {
    private static final String MESSAGE ="Girilen id ile ilişkili bir ürün bulunamadı. Id: {0}";

    public AmountTypeNotFoundException(Integer id) {
        super(MessageFormat.format(MESSAGE, id));
    }
}
