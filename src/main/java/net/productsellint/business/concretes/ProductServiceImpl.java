package net.productsellint.business.concretes;

import net.productsellint.business.abstracts.AmountTypeService;
import net.productsellint.business.abstracts.CategoryService;
import net.productsellint.business.abstracts.ProductService;
import net.productsellint.dataAccess.abstracts.ProductDao;
import net.productsellint.dataTransferObjects.concretes.ProductDto;
import net.productsellint.dataTransferObjects.concretes.ProductRequest;
import net.productsellint.entities.concretes.AmountTypeEntity;
import net.productsellint.entities.concretes.CategoryEntity;
import net.productsellint.entities.concretes.EntityStatus;
import net.productsellint.entities.concretes.ProductEntity;
import net.productsellint.exception.custom.ProductNotFoundException;
import org.modelmapper.ModelMapper;
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
    private final CategoryService categoryService;
    private final AmountTypeService amountTypeService;
    private final ModelMapper mapper;

    public ProductServiceImpl(ProductDao productDao, CategoryService categoryService, AmountTypeService amountTypeService, ModelMapper mapper) {
        this.productDao = productDao;
        this.categoryService = categoryService;
        this.amountTypeService = amountTypeService;
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
        CategoryEntity categoryEntity = this.categoryService.findById(productRequest.getCategoryId());
        AmountTypeEntity amountTypeEntity = this.amountTypeService.findById(productRequest.getAmountTypeId());
        EntityStatus entityStatus = EntityStatus.getStatus(productRequest.getStatus());
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
