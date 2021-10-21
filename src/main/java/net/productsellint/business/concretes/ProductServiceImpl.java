package net.productsellint.business.concretes;

import net.productsellint.business.abstracts.ProductService;
import net.productsellint.core.utilities.results.*;
import net.productsellint.dataAccess.abstracts.AmountTypeDao;
import net.productsellint.dataAccess.abstracts.CategoryDao;
import net.productsellint.dataAccess.abstracts.ProductDao;
import net.productsellint.dataTransferObjects.concretes.ProductDto;
import net.productsellint.entities.concretes.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;
    private final CategoryDao categoryDao;
    private final AmountTypeDao amountTypeDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao, CategoryDao categoryDao, AmountTypeDao amountTypeDao) {
        super();
        this.productDao = productDao;
        this.categoryDao = categoryDao;
        this.amountTypeDao = amountTypeDao;
    }

    @Override
    public DataResult<List<ProductDto>> getAll() {
        return new SuccessDataResult<List<ProductDto>>(
            this.productDao.findAll()
                .stream()
                .map(ProductDto::new)
                .collect(Collectors.toList()),
            "Veri Listelendi.");
    }

    @Override
    public DataResult<List<ProductDto>> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return new SuccessDataResult<List<ProductDto>>(
            this.productDao.findAll(pageable)
                .getContent()
                .stream()
                .map(ProductDto::new)
                .collect(Collectors.toList()),
            "Veri Listelendi.");

    }

    @Override
    public DataResult<List<ProductDto>> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.ASC, "productName");
        return new SuccessDataResult<List<ProductDto>>(
            this.productDao.findAll(sort)
                .stream()
                .map(ProductDto::new)
                .collect(Collectors.toList()), "Veri Listelendi.");
    }

    @Override
    public Result add(ProductDto productDto) {
        ///Category categoryEntity = this.categoryDao.findById(productDto.getCategoryId());
        ProductEntity productEntity = new ProductEntity(
                productDto.getId(),
                productDto.getProductName(),
                productDto.getUnitPrice(),
                this.categoryDao.getById(productDto.getCategoryId()),
                this.amountTypeDao.getById(productDto.getAmountTypeId())
        );
        try{
            this.productDao.save(productEntity);
            return new SuccessResult("Ürün eklendi.");
        } catch (Exception e) {
            return new ErrorResult(e.toString());
        }
    }

    @Override
    public Result drop(Integer id) {
        try{
            this.productDao.deleteById(id);
            return new SuccessResult("Ürün silindi.");
        } catch (Exception e) {
            return new ErrorResult(e.toString());
        }
    }

    @Override
    public DataResult<ProductDto> getByProductName(String productName) {
        return new SuccessDataResult<ProductDto>(
            new ProductDto(this.productDao.getByProductName(productName)),
            "Veri Listelendi.");
    }
}
