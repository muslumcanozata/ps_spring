package net.productsellint.exception.custom;

import net.productsellint.exception.base.ValidationException;

import java.text.MessageFormat;

public class CategoryNotFoundException extends ValidationException {

    private static final String MESSAGE = "Girilen {0} ile ilişkili bir kategori bulunamadı. Id: {1}";

    public CategoryNotFoundException(Integer id) {
        super(MessageFormat.format(MESSAGE, "id", id));
    }
    public CategoryNotFoundException(String name) {
        super(MessageFormat.format(MESSAGE, "isim", name));
    }

}
