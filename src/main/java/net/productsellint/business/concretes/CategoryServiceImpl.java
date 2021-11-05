package net.productsellint.business.concretes;

import net.productsellint.business.abstracts.CategoryService;
import net.productsellint.dataAccess.abstracts.CategoryDao;
import net.productsellint.dataTransferObjects.concretes.CategoryDto;
import net.productsellint.entities.concretes.CategoryEntity;
import net.productsellint.entities.concretes.CategoryRequest;
import net.productsellint.entities.concretes.EntityStatus;
import net.productsellint.exception.custom.CategoryNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    public final CategoryDao categoryDao;
    public final ModelMapper mapper;

    public CategoryServiceImpl(CategoryDao categoryDao, ModelMapper mapper) {
        this.categoryDao = categoryDao;
        this.mapper = mapper;
    }

    @Override
    public List<CategoryDto> getAll() {
        return this.categoryDao.findByEntityStatus(EntityStatus.ACTIVE)
            .stream()
            .map(categoryEntity -> mapper.map(categoryEntity, CategoryDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public void add(CategoryRequest categoryRequest) {
        CategoryEntity categoryEntity = mapper.map(categoryRequest, CategoryEntity.class);
        this.categoryDao.save(categoryEntity);
    }

    @Override
    public void deleteCategory(Integer id) {
        this.categoryDao.deleteCategory(id);
    }

    @Override
    public void activateCategory(Integer id) {
        this.categoryDao.activateCategory(id);
    }

    @Override
    public void disableCategory(Integer id) {
        this.categoryDao.disableCategory(id);
    }

    @Override
    public CategoryDto getByCategoryName(String categoryName) {
        CategoryEntity categoryEntity = this.categoryDao.getByCategoryName(categoryName);
        return mapper.map(categoryEntity, CategoryDto.class);
    }

    @Override
    public CategoryDto getById(Integer id) {
        return mapper.map(this.categoryDao.getById(id), CategoryDto.class);
    }

    @Override
    public CategoryEntity findById(Integer id) {
        CategoryEntity categoryEntity = this.categoryDao.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        return categoryEntity;
    }
}
