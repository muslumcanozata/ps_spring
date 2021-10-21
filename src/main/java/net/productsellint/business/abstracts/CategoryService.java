package net.productsellint.business.abstracts;

import net.productsellint.core.utilities.results.DataResult;
import net.productsellint.core.utilities.results.Result;
import net.productsellint.dataTransferObjects.concretes.CategoryDto;

import java.util.List;

public interface CategoryService {
    DataResult<List<CategoryDto>> getAll();

    Result add(CategoryDto categoryDto);

    Result drop(Integer id);

    DataResult<CategoryDto> getByCategoryName(String categoryName);
    DataResult<CategoryDto> getById(Integer id);

}
