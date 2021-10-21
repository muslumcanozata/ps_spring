package net.productsellint.business.concretes;

import net.productsellint.business.abstracts.CategoryService;
import net.productsellint.core.utilities.results.*;
import net.productsellint.dataAccess.abstracts.CategoryDao;
import net.productsellint.dataTransferObjects.concretes.CategoryDto;
import net.productsellint.entities.concretes.CategoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public DataResult<List<CategoryDto>> getAll() {
        return new SuccessDataResult<List<CategoryDto>>(
            this.categoryDao.findAll()
            .stream()
            .map(categoryEntity -> mapper.map(categoryEntity, CategoryDto.class))
            .collect(Collectors.toList()),
            "Veriler Listelendi.");
    }

    @Override
    public Result add(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = mapper.map(categoryDto, CategoryEntity.class);
        try{
            this.categoryDao.save(categoryEntity);
            return new SuccessResult("Ürün eklendi.");
        } catch (Exception e) {
            return new ErrorResult(e.toString());
        }
    }

    @Override
    public Result drop(Integer id) {
        try{
            this.categoryDao.deleteById(id);
            return new SuccessResult("Ürün silindi.");
        } catch (Exception e) {
            return new ErrorResult(e.toString());
        }
    }

    @Override
    public DataResult<CategoryDto> getByCategoryName(String categoryName) {
        return new SuccessDataResult<CategoryDto>(mapper.map(this.categoryDao.getByCategoryName(categoryName), CategoryDto.class), "Veri Listelendi.");
    }

    @Override
    public DataResult<CategoryDto> getById(Integer id) {
        return new SuccessDataResult<CategoryDto>(mapper.map(this.categoryDao.getById(id), CategoryDto.class), "Veri Listelendi.");
    }
}
