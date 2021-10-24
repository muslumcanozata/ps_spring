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
import net.productsellint.exception.custom.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<DataResult<List<ProductDto>>> getAll() {
        List<ProductDto> data = this.productDao.findByEntityStatus(EntityStatus.ACTIVE)
                .stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.status(200).body(new SuccessDataResult<List<ProductDto>>(data, "Veri Listelendi."));
    }

    @Override
    public ResponseEntity<DataResult<List<ProductDto>>> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        List<ProductDto> data = this.productDao.findByEntityStatus(EntityStatus.ACTIVE, pageable)
                .stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.status(200).body(new SuccessDataResult<>(data, "Veri Listelendi."));
    }

    @Override
    public ResponseEntity<DataResult<List<ProductDto>>> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.ASC, "productName");
        List<ProductDto> data = this.productDao.findByEntityStatus(EntityStatus.ACTIVE, sort)
                .stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.status(200).body(new SuccessDataResult<>(data, "Veri Listelendi."));
    }

    @Override
    public ResponseEntity<Result> add(ProductDto productDto) {
        CategoryEntity categoryEntity = this.categoryDao.findById(productDto.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException(productDto.getCategoryId()));
        AmountTypeEntity amountTypeEntity = this.amountTypeDao.findById(productDto.getAmountTypeId()).orElseThrow();
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
        return ResponseEntity.status(200).body(new SuccessResult("Ürün Eklendi."));
    }

    @Override
    public ResponseEntity<Result> deleteProduct(Integer id) {
        this.productDao.deleteProduct(id);
        return ResponseEntity.status(200).body(new SuccessResult("Ürün silindi."));
    }

    @Override
    public ResponseEntity<Result> activateProduct(Integer id) {
        this.productDao.activateProduct(id);
        return ResponseEntity.status(200).body(new SuccessResult("Ürün aktif."));
    }

    @Override
    public ResponseEntity<Result> disableProduct(Integer id) {
        this.productDao.disableProduct(id);
        return ResponseEntity.status(200).body(new SuccessResult("Ürün devre dışı."));
    }

    @Override
    public ResponseEntity<DataResult<ProductDto>> getByProductNameAndEntityStatus(String productName, EntityStatus entityStatus) {
        return ResponseEntity.status(200).body(new SuccessDataResult<>(
            new ProductDto(this.productDao.getByProductNameAndEntityStatus(productName, entityStatus)),
            "Veri Listelendi."));
    }
}
