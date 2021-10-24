package net.productsellint.business.concretes;

import net.productsellint.business.abstracts.CategoryService;
import net.productsellint.core.utilities.results.*;
import net.productsellint.dataAccess.abstracts.CategoryDao;
import net.productsellint.dataTransferObjects.concretes.CategoryDto;
import net.productsellint.dataTransferObjects.concretes.ProductDto;
import net.productsellint.entities.concretes.CategoryEntity;
import net.productsellint.entities.concretes.EntityStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;
    private final ModelMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao, ModelMapper mapper) {
        super();
        this.categoryDao = categoryDao;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<DataResult<List<CategoryDto>>> getAll() {
        return ResponseEntity.status(200).body(new SuccessDataResult<List<CategoryDto>>(
            this.categoryDao.findByEntityStatus(EntityStatus.ACTIVE)
            .stream()
            .map(categoryEntity -> mapper.map(categoryEntity, CategoryDto.class))
            .collect(Collectors.toList()),
            "Veriler Listelendi."));
    }

    @Override
    public ResponseEntity<Result>  add(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = mapper.map(categoryDto, CategoryEntity.class);
        this.categoryDao.save(categoryEntity);
        return ResponseEntity.status(200).body(new SuccessResult("Ürün eklendi."));
    }

    @Override
    public ResponseEntity<Result> deleteCategory(Integer id) {
        this.categoryDao.deleteCategory(id);
        return ResponseEntity.status(200).body(new SuccessResult("Ürün silindi."));
    }

    @Override
    public ResponseEntity<Result> activateCategory(Integer id) {
        this.categoryDao.activateCategory(id);
        return ResponseEntity.status(200).body(new SuccessResult("Ürün silindi."));
    }

    @Override
    public ResponseEntity<Result> disableCategory(Integer id) {
        this.categoryDao.disableCategory(id);
        return ResponseEntity.status(200).body(new SuccessResult("Ürün silindi."));
    }

    @Override
    public ResponseEntity<DataResult<CategoryDto>> getByCategoryName(String categoryName) {
        return ResponseEntity.status(200).body(new SuccessDataResult<CategoryDto>(mapper.map(this.categoryDao.getByCategoryName(categoryName), CategoryDto.class), "Veri Listelendi."));
    }

    @Override
    public ResponseEntity<DataResult<CategoryDto>> getById(Integer id) {
        return ResponseEntity.status(200).body(new SuccessDataResult<CategoryDto>(mapper.map(this.categoryDao.getById(id), CategoryDto.class), "Veri Listelendi."));
    }
}
