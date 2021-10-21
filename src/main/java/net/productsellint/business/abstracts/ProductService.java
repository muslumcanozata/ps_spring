package net.productsellint.business.abstracts;

import net.productsellint.core.utilities.results.DataResult;
import net.productsellint.core.utilities.results.Result;
import net.productsellint.dataTransferObjects.concretes.ProductDto;

import java.util.List;

public interface ProductService {
    DataResult<List<ProductDto>> getAll();
    DataResult<List<ProductDto>> getAll(int pageNo, int pageSize);
    DataResult<List<ProductDto>> getAllSorted();

    Result add(ProductDto productDto);

    Result drop(Integer id);

    DataResult<ProductDto> getByProductName(String productName);


}
