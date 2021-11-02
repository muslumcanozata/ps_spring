package net.productsellint.business.abstracts;

import net.productsellint.dataTransferObjects.concretes.AmountTypeDto;
import net.productsellint.dataTransferObjects.concretes.AmountTypeRequest;

import java.util.List;

public interface AmountTypeService {
    List<AmountTypeDto> getAll();

    void add(AmountTypeRequest amountTypeRequest);

    void deleteAmountType(Integer id);
    void activateAmountType(Integer id);
    void disableAmountType(Integer id);

    AmountTypeDto getByType(String type);
    AmountTypeDto getById(Integer id);
}
