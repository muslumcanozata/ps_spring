package net.productsellint.business.abstracts;

import net.productsellint.core.utilities.results.DataResult;
import net.productsellint.core.utilities.results.Result;
import net.productsellint.dataTransferObjects.concretes.AmountTypeDto;

import java.util.List;

public interface AmountTypeService {
    DataResult<List<AmountTypeDto>> getAll();

    Result add(AmountTypeDto amountTypeDto);

    Result drop(Integer id);

    DataResult<AmountTypeDto> getByType(String type);
    DataResult<AmountTypeDto> getById(Integer id);
}
