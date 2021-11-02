package net.productsellint.business.concretes;

import net.productsellint.business.abstracts.ProductService;
import net.productsellint.dataAccess.abstracts.AmountTypeDao;
import net.productsellint.dataAccess.abstracts.CategoryDao;
import net.productsellint.dataAccess.abstracts.ProductDao;
import net.productsellint.dataTransferObjects.concretes.ProductDto;
import net.productsellint.dataTransferObjects.concretes.ProductRequest;
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
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
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

    @Override
    public List<ProductDto> getAll() {
        return this.productDao.findByEntityStatus(EntityStatus.ACTIVE)
                .stream()
                .map(productEntity -> mapper.map(productEntity, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return this.productDao.findByEntityStatus(EntityStatus.ACTIVE, pageable)
                .stream()
                .map(productEntity -> mapper.map(productEntity, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.ASC, "productName");
        return this.productDao.findByEntityStatus(EntityStatus.ACTIVE, sort)
                .stream()
                .map(productEntity -> mapper.map(productEntity, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void add(ProductRequest productRequest) {
        CategoryEntity categoryEntity = this.categoryDao.findById(productRequest.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException(productRequest.getCategoryId()));
        AmountTypeEntity amountTypeEntity = this.amountTypeDao.findById(productRequest.getAmountTypeId()).orElseThrow(() -> new AmountTypeNotFoundException(productRequest.getAmountTypeId()));
        EntityStatus entityStatus = EntityStatus.ACTIVE;
        ProductEntity productEntity = new ProductEntity(
                productRequest.getProductName(),
                productRequest.getUnitPrice(),
                categoryEntity,
                amountTypeEntity,
                productRequest.getStock(),
                entityStatus
        );
        this.productDao.save(productEntity);
    }

    @Override
    public void deleteProduct(Integer id) {
        this.productDao.deleteProduct(id);
    }

    @Override
    public void activateProduct(Integer id) {
        this.productDao.activateProduct(id);
    }

    @Override
    public void disableProduct(Integer id) {
        this.productDao.disableProduct(id);
    }

    @Override
    public ProductDto getByProductNameAndEntityStatus(String productName, EntityStatus entityStatus) {
        return mapper.map(this.productDao.getByProductNameAndEntityStatus(productName, entityStatus), ProductDto.class);
    }
}
