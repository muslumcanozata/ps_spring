package net.productsellint.business.abstracts;

import net.productsellint.core.utilities.results.DataResult;
import net.productsellint.core.utilities.results.Result;
import net.productsellint.dataTransferObjects.concretes.AmountTypeDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AmountTypeService {
    ResponseEntity<DataResult<List<AmountTypeDto>>> getAll();

    ResponseEntity<Result> add(AmountTypeDto amountTypeDto);

    ResponseEntity<Result> deleteAmountType(Integer id);
    ResponseEntity<Result> activateAmountType(Integer id);
    ResponseEntity<Result> disableAmountType(Integer id);


    ResponseEntity<DataResult<AmountTypeDto>> getByType(String type);
    ResponseEntity<DataResult<AmountTypeDto>> getById(Integer id);
}
