package net.productsellint.servicesTest;


import net.productsellint.business.concretes.AmountTypeServiceImpl;
import net.productsellint.business.concretes.CategoryServiceImpl;
import net.productsellint.business.concretes.ProductServiceImpl;
import net.productsellint.dataAccess.abstracts.ProductDao;
import net.productsellint.dataTransferObjects.concretes.ProductDto;
import net.productsellint.dataTransferObjects.concretes.ProductRequest;
import net.productsellint.entities.concretes.AmountTypeEntity;
import net.productsellint.entities.concretes.CategoryEntity;
import net.productsellint.entities.concretes.EntityStatus;
import net.productsellint.entities.concretes.ProductEntity;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
public class ProductServiceTest {
    @Mock
    private ProductDao productDao;

    @Mock
    private CategoryServiceImpl categoryServiceImpl;

    @Mock
    private AmountTypeServiceImpl amountTypeServiceImpl;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    ProductServiceImpl productServiceImpl;

    @Test
    public void testGetAll() {
        List<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(new ProductEntity());
        productEntityList.add(new ProductEntity());
        productEntityList.get(0).setId(1);
        productEntityList.get(0).setProductName("Prod1");
        productEntityList.get(1).setId(1);
        productEntityList.get(1).setProductName("Prod2");

        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(new ProductDto());
        productDtoList.add(new ProductDto());
        productDtoList.get(0).setId(1);
        productDtoList.get(0).setProductName("Prod1");
        productDtoList.get(1).setId(1);
        productDtoList.get(1).setProductName("Prod2");

        when(productDao.findByEntityStatus(EntityStatus.ACTIVE)).thenReturn(productEntityList);
        when(mapper.map(productEntityList.get(0), ProductDto.class)).thenReturn(productDtoList.get(0));
        when(mapper.map(productEntityList.get(1), ProductDto.class)).thenReturn(productDtoList.get(1));

        List<ProductDto> productDtos = productServiceImpl.getAll();

        assertEquals(productDtos.get(0).getId(), productDtoList.get(0).getId());
        assertEquals(productDtos.get(0).getProductName(), productDtoList.get(0).getProductName());
        assertEquals(productDtos.get(1).getId(), productDtoList.get(1).getId());
        assertEquals(productDtos.get(1).getProductName(), productDtoList.get(1).getProductName());
    }

    @Test
    public void testGetAllPageable() {
        int pageNo = 1;
        int pageSize = 2;
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);

        List<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(new ProductEntity());
        productEntityList.add(new ProductEntity());
        productEntityList.get(0).setId(1);
        productEntityList.get(0).setProductName("Prod1");
        productEntityList.get(1).setId(2);
        productEntityList.get(1).setProductName("Prod2");

        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(new ProductDto());
        productDtoList.add(new ProductDto());
        productDtoList.get(0).setId(1);
        productDtoList.get(0).setProductName("Prod1");
        productDtoList.get(1).setId(2);
        productDtoList.get(1).setProductName("Prod2");

        when(productDao.findByEntityStatus(EntityStatus.ACTIVE, pageable)).thenReturn(productEntityList);
        when(mapper.map(productEntityList.get(0), ProductDto.class)).thenReturn(productDtoList.get(0));
        when(mapper.map(productEntityList.get(1), ProductDto.class)).thenReturn(productDtoList.get(1));

        List<ProductDto> productDtos = productServiceImpl.getAll(pageNo, pageSize);

        assertEquals(productDtos.get(0).getId(), productDtoList.get(0).getId());
        assertEquals(productDtos.get(0).getProductName(), productDtoList.get(0).getProductName());
        assertEquals(productDtos.get(1).getId(), productDtoList.get(1).getId());
        assertEquals(productDtos.get(1).getProductName(), productDtoList.get(1).getProductName());
    }

    @Test
    public void testGetAllSorted() {
        Sort sort = Sort.by(Sort.Direction.ASC, "productName");
        List<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(new ProductEntity());
        productEntityList.add(new ProductEntity());
        productEntityList.get(0).setId(1);
        productEntityList.get(0).setProductName("Prod1");
        productEntityList.get(1).setId(2);
        productEntityList.get(1).setProductName("Prod2");

        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(new ProductDto());
        productDtoList.add(new ProductDto());
        productDtoList.get(0).setId(1);
        productDtoList.get(0).setProductName("Prod1");
        productDtoList.get(1).setId(2);
        productDtoList.get(1).setProductName("Prod2");

        when(productDao.findByEntityStatus(EntityStatus.ACTIVE, sort)).thenReturn(productEntityList);
        when(mapper.map(productEntityList.get(0), ProductDto.class)).thenReturn(productDtoList.get(0));
        when(mapper.map(productEntityList.get(1), ProductDto.class)).thenReturn(productDtoList.get(1));

        List<ProductDto> productDtos = productServiceImpl.getAllSorted();

        assertEquals(productDtos.get(0).getId(), productDtoList.get(0).getId());
        assertEquals(productDtos.get(0).getProductName(), productDtoList.get(0).getProductName());
        assertEquals(productDtos.get(1).getId(), productDtoList.get(1).getId());
        assertEquals(productDtos.get(1).getProductName(), productDtoList.get(1).getProductName());
    }


    @Test
    public void testGetByProductNameAndEntityStatus() {
        String name = "PORTAKAL";

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1);
        productEntity.setProductName("PORTAKAL");

        ProductDto productDto = new ProductDto();
        productDto.setId(1);
        productDto.setProductName("PORTAKAL");

        when(mapper.map(productEntity,ProductDto.class)).thenReturn(productDto);
        when(this.productDao.getByProductNameAndEntityStatus(name,EntityStatus.ACTIVE)).thenReturn(productEntity);
        ProductDto productDto1 = productServiceImpl.getByProductNameAndEntityStatus(productEntity.getProductName(), EntityStatus.ACTIVE);
        
        assertEquals(productDto1.getId(),productDto.getId());
        assertEquals(productDto1.getProductName(), productDto.getProductName());
    }

    @Test
    public void testAdd() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1);
        AmountTypeEntity amountTypeEntity = new AmountTypeEntity();
        amountTypeEntity.setId(1);
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategoryEntity(categoryEntity);
        productEntity.setAmountTypeEntity(amountTypeEntity);
        productEntity.setEntityStatus(EntityStatus.ACTIVE);
        ProductRequest productRequest = new ProductRequest();
        productRequest.setCategoryId(1);
        productRequest.setAmountTypeId(1);
        productRequest.setStatus(0);
        EntityStatus entityStatus = EntityStatus.ACTIVE;

        when(categoryServiceImpl.findById(productRequest.getCategoryId())).thenReturn(categoryEntity);
        when(amountTypeServiceImpl.findById(productRequest.getAmountTypeId())).thenReturn(amountTypeEntity);
        ///doNothing().doThrow(RuntimeException.class).when(productDao).save(productEntity);

        productServiceImpl.add(productRequest);

        Mockito.verify(productDao, Mockito.times(1)).save(productEntity);
    }

    @Test
    public void testActivateProduct() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(3);

        doNothing().doThrow(RuntimeException.class).when(productDao).activateProduct(productEntity.getId());

        productServiceImpl.activateProduct(productEntity.getId());
        Mockito.verify(productDao, Mockito.times(1)).activateProduct(productEntity.getId());
    }

    @Test
    public void testDeleteProduct() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(3);

        doNothing().doThrow(RuntimeException.class).when(productDao).deleteProduct(productEntity.getId());

        productServiceImpl.deleteProduct(productEntity.getId());
        Mockito.verify(productDao, Mockito.times(1)).deleteProduct(productEntity.getId());
    }

    @Test
    public void testDisableProduct() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(3);

        ///doNothing().doThrow(RuntimeException.class).when(categoryDao).save(categoryEntity);

        ///when(this.mapper.map(categoryDto,CategoryEntity.class)).thenReturn(categoryEntity);

        productServiceImpl.disableProduct(productEntity.getId());

        Mockito.verify(productDao, Mockito.times(1)).disableProduct(productEntity.getId());
    }
}
