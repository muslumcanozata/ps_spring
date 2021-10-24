package net.productsellint.exception.custom;

import net.productsellint.exception.base.ValidationException;

import java.text.MessageFormat;

public class ProductNotFoundException extends ValidationException {
    private static final String MESSAGE ="Girilen id ile ilişkili bir ürün bulunamadı. Id: {0}";

    public ProductNotFoundException(Integer id) {
        super(MessageFormat.format(MESSAGE, id));
    }
}
