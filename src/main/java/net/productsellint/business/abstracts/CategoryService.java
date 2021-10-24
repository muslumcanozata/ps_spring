package net.productsellint.business.abstracts;

import net.productsellint.core.utilities.results.DataResult;
import net.productsellint.core.utilities.results.Result;
import net.productsellint.dataTransferObjects.concretes.CategoryDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    ResponseEntity<DataResult<List<CategoryDto>>> getAll();

    ResponseEntity<Result>  add(CategoryDto categoryDto);

    ResponseEntity<Result> deleteCategory(Integer id);
    ResponseEntity<Result> activateCategory(Integer id);
    ResponseEntity<Result> disableCategory(Integer id);

    ResponseEntity<DataResult<CategoryDto>> getByCategoryName(String categoryName);
    ResponseEntity<DataResult<CategoryDto>> getById(Integer id);

}
