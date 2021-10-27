package net.productsellint.business.concretes;

import net.productsellint.business.abstracts.ProductService;
import net.productsellint.core.utilities.results.*;
import net.productsellint.dataAccess.abstracts.AmountTypeDao;
import net.productsellint.dataAccess.abstracts.CategoryDao;
import net.productsellint.dataAccess.abstracts.ProductDao;
import net.productsellint.dataTransferObjects.concretes.ProductDto;
import net.productsellint.entities.concretes.AmountTypeEntity;
import net.productsellint.entities.concretes.CategoryEntity;
import net.productsellint.entities.concretes.EntityStatus;
import net.productsellint.entities.concretes.ProductEntity;
import net.productsellint.exception.custom.AmountTypeNotFoundException;
import net.productsellint.exception.custom.CategoryNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl {
    private final ProductDao productDao;
    private final CategoryDao categoryDao;
    private final AmountTypeDao amountTypeDao;
    private final ModelMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductDao productDao, CategoryDao categoryDao, AmountTypeDao amountTypeDao, ModelMapper mapper) {
        super();
        this.productDao = productDao;
        this.categoryDao = categoryDao;
        this.amountTypeDao = amountTypeDao;
        this.mapper = mapper;
    }

    public List<ProductDto> getAll() {
        return this.productDao.findByEntityStatus(EntityStatus.ACTIVE)
                .stream()
                .map(productEntity -> mapper.map(productEntity, ProductDto.class))
                .collect(Collectors.toList());
    }

    public List<ProductDto> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return this.productDao.findByEntityStatus(EntityStatus.ACTIVE, pageable)
                .stream()
                .map(productEntity -> mapper.map(productEntity, ProductDto.class))
                .collect(Collectors.toList());
    }

    public List<ProductDto> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.ASC, "productName");
        return this.productDao.findByEntityStatus(EntityStatus.ACTIVE, sort)
                .stream()
                .map(productEntity -> mapper.map(productEntity, ProductDto.class))
                .collect(Collectors.toList());
    }

    public void add(ProductDto productDto) {
        CategoryEntity categoryEntity = this.categoryDao.findById(productDto.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException(productDto.getCategoryId()));
        AmountTypeEntity amountTypeEntity = this.amountTypeDao.findById(productDto.getAmountTypeId()).orElseThrow(() -> new AmountTypeNotFoundException(productDto.getAmountTypeId()));
        EntityStatus entityStatus = EntityStatus.ACTIVE;
        ProductEntity productEntity = new ProductEntity(
                productDto.getId(),
                productDto.getProductName(),
                productDto.getUnitPrice(),
                categoryEntity,
                amountTypeEntity,
                entityStatus
        );
        this.productDao.save(productEntity);
    }

    public void deleteProduct(Integer id) {
        this.productDao.deleteProduct(id);
    }

    public void activateProduct(Integer id) {
        this.productDao.activateProduct(id);
    }

    public void disableProduct(Integer id) {
        this.productDao.disableProduct(id);
    }

    public ProductDto getByProductNameAndEntityStatus(String productName, EntityStatus entityStatus) {
        return mapper.map(this.productDao.getByProductNameAndEntityStatus(productName, entityStatus), ProductDto.class);
    }
}
