package net.productsellint.business.concretes;

import net.productsellint.dataAccess.abstracts.CategoryDao;
import net.productsellint.dataTransferObjects.concretes.AmountTypeDto;
import net.productsellint.dataTransferObjects.concretes.CategoryDto;
import net.productsellint.entities.concretes.CategoryEntity;
import net.productsellint.entities.concretes.EntityStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl {
    public final CategoryDao categoryDao;
    public final ModelMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao, ModelMapper mapper) {
        super();
        this.categoryDao = categoryDao;
        this.mapper = mapper;
    }

    public List<CategoryDto> getAll() {
        return this.categoryDao.findByEntityStatus(EntityStatus.ACTIVE)
            .stream()
            .map(categoryEntity -> mapper.map(categoryEntity, CategoryDto.class))
            .collect(Collectors.toList());
    }

    public void add(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = mapper.map(categoryDto, CategoryEntity.class);
        this.categoryDao.save(categoryEntity);
    }

    public void deleteCategory(Integer id) {
        this.categoryDao.deleteCategory(id);
    }

    public void activateCategory(Integer id) {
        this.categoryDao.activateCategory(id);
    }

    public void disableCategory(Integer id) {
        this.categoryDao.disableCategory(id);
    }

    public CategoryDto getByCategoryName(String categoryName) {
        CategoryEntity categoryEntity = this.categoryDao.getByCategoryName(categoryName);
        return mapper.map(categoryEntity, CategoryDto.class);
    }

    public CategoryDto getById(Integer id) {
        return mapper.map(this.categoryDao.getById(id), CategoryDto.class);
    }
}
