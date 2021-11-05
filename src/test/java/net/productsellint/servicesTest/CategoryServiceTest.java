package net.productsellint.servicesTest;

import net.productsellint.business.concretes.CategoryServiceImpl;
import net.productsellint.dataAccess.abstracts.CategoryDao;
import net.productsellint.dataTransferObjects.concretes.CategoryDto;
import net.productsellint.entities.concretes.CategoryEntity;
import net.productsellint.entities.concretes.CategoryRequest;
import net.productsellint.entities.concretes.EntityStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
public class CategoryServiceTest {
    @Mock
    CategoryDao categoryDao;

    @Mock
    ModelMapper mapper;

    @InjectMocks
    CategoryServiceImpl categoryServiceImpl;

    @Test
    public void testGetAll() {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        List<CategoryEntity> categoryEntityList = new ArrayList<>();

        categoryEntityList.add(new CategoryEntity(4, "KAHVALTILIK", EntityStatus.ACTIVE));
        categoryEntityList.add(new CategoryEntity(5, "KAHVALTILIK", EntityStatus.ACTIVE));

        categoryDtoList.add(new CategoryDto(4, "KAHVALTILIK", 0));
        categoryDtoList.add(new CategoryDto(5, "KAHVALTILIK", 0));

        when(categoryDao.findByEntityStatus(EntityStatus.ACTIVE)).thenReturn(categoryEntityList);
        when(mapper.map(categoryEntityList.get(0), CategoryDto.class)).thenReturn(categoryDtoList.get(0));
        when(mapper.map(categoryEntityList.get(1), CategoryDto.class)).thenReturn(categoryDtoList.get(1));

        List<CategoryDto> categoryDtoList1 = categoryServiceImpl.getAll();

        assertEquals(categoryDtoList1.get(0).getId(), categoryDtoList.get(0).getId());
        assertEquals(categoryDtoList1.get(0).getCategoryName(), categoryDtoList.get(0).getCategoryName());
        assertEquals(categoryDtoList1.get(1).getId(), categoryDtoList.get(1).getId());
        assertEquals(categoryDtoList1.get(1).getCategoryName(), categoryDtoList.get(1).getCategoryName());
    }

    @Test
    public void testAdd() {
        CategoryEntity categoryEntity = new CategoryEntity(4, "KAHVALTILIK", EntityStatus.ACTIVE);
        CategoryRequest categoryRequest = new CategoryRequest("KAHVALTILIK", 0);

        when(mapper.map(categoryRequest, CategoryEntity.class)).thenReturn(categoryEntity);

        ///doNothing().doThrow(RuntimeException.class).when(categoryDao).save(categoryEntity);

        categoryServiceImpl.add(categoryRequest);

        Mockito.verify(categoryDao, Mockito.times(1)).save(categoryEntity);
    }

    @Test
    public void testActivateCategory() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(3);

        doNothing().doThrow(RuntimeException.class).when(categoryDao).activateCategory(categoryEntity.getId());

        categoryServiceImpl.activateCategory(categoryEntity.getId());
        Mockito.verify(categoryDao, Mockito.times(1)).activateCategory(categoryEntity.getId());
    }

    @Test
    public void testDeleteCategory() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(3);

        doNothing().doThrow(RuntimeException.class).when(categoryDao).deleteCategory(categoryEntity.getId());

        categoryServiceImpl.deleteCategory(categoryEntity.getId());
        Mockito.verify(categoryDao, Mockito.times(1)).deleteCategory(categoryEntity.getId());
    }

    @Test
    public void testDisableCategory() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(3);

        //doNothing().doThrow(RuntimeException.class).when(categoryDao).save(categoryEntity);

        //when(this.mapper.map(categoryDto,CategoryEntity.class)).thenReturn(categoryEntity);

        categoryServiceImpl.disableCategory(categoryEntity.getId());

        Mockito.verify(categoryDao, Mockito.times(1)).disableCategory(categoryEntity.getId());
    }

    @Test
    public void testGetById() {
        Integer id = 3;

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(3);
        categoryEntity.setEntityStatus(EntityStatus.ACTIVE);
        categoryEntity.setCategoryName("TEMEL GIDA");

        CategoryDto dto = new CategoryDto();
        dto.setCategoryName(categoryEntity.getCategoryName());
        dto.setStatus(0);
        dto.setId(categoryEntity.getId());

        when(this.categoryDao.getById(id)).thenReturn(categoryEntity);
        when(this.mapper.map(categoryEntity,CategoryDto.class)).thenReturn(dto);
        CategoryDto categoryDto = categoryServiceImpl.getById(categoryEntity.getId());

        assertEquals(categoryEntity.getId(), categoryDto.getId());
    }

    @Test
    public void testGetByCategoryName() {
        String name = "TEMEL GIDA";

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(3);
        categoryEntity.setEntityStatus(EntityStatus.ACTIVE);
        categoryEntity.setCategoryName("TEMEL GIDA");

        CategoryDto dto = new CategoryDto();
        dto.setCategoryName(categoryEntity.getCategoryName());
        dto.setStatus(0);
        dto.setId(categoryEntity.getId());

        when(this.categoryDao.getByCategoryName(name)).thenReturn(categoryEntity);
        when(this.mapper.map(categoryEntity,CategoryDto.class)).thenReturn(dto);
        CategoryDto categoryDto = categoryServiceImpl.getByCategoryName(categoryEntity.getCategoryName());

        assertEquals(categoryEntity.getCategoryName(), categoryDto.getCategoryName());
    }

    @Test
    public void testFindById() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(3);
        categoryEntity.setEntityStatus(EntityStatus.ACTIVE);
        categoryEntity.setCategoryName("");

        when(categoryDao.findById(categoryEntity.getId())).thenReturn(Optional.of(categoryEntity));

        CategoryEntity categoryEntity1 = categoryServiceImpl.findById(categoryEntity.getId());

        assertEquals(categoryEntity1.getId(), categoryEntity.getId());
        assertEquals(categoryEntity1.getCategoryName(), categoryEntity.getCategoryName());
    }
}