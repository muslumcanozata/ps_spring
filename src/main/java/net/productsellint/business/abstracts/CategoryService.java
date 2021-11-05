package net.productsellint.business.abstracts;

import net.productsellint.dataTransferObjects.concretes.CategoryDto;
import net.productsellint.entities.concretes.CategoryEntity;
import net.productsellint.entities.concretes.CategoryRequest;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll();

    void add(CategoryRequest categoryRequest);
    void deleteCategory(Integer id);
    void activateCategory(Integer id);
    void disableCategory(Integer id);

    CategoryDto getByCategoryName(String categoryName);
    CategoryDto getById(Integer id);

    CategoryEntity findById(Integer id);
}
