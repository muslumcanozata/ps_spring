package net.productsellint.servicesTest;


import net.productsellint.business.concretes.AmountTypeServiceImpl;
import net.productsellint.dataAccess.abstracts.AmountTypeDao;
import net.productsellint.dataTransferObjects.concretes.AmountTypeDto;
import net.productsellint.dataTransferObjects.concretes.CategoryDto;
import net.productsellint.entities.concretes.AmountTypeEntity;
import net.productsellint.entities.concretes.CategoryEntity;
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

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
public class AmountTypeServiceTest {
    @Mock
    AmountTypeDao amountTypeDao;

    @Mock
    ModelMapper mapper;

    @InjectMocks
    AmountTypeServiceImpl amountTypeServiceImpl;

    @Test
    public void testGetAll() {
        List<AmountTypeEntity> amountTypeEntity = new ArrayList<>();
        amountTypeEntity.add(new AmountTypeEntity(1,"Kilogram", EntityStatus.ACTIVE));
        amountTypeEntity.add(new AmountTypeEntity(2,"Kilogram", EntityStatus.ACTIVE));

        List<AmountTypeDto> amountTypeDto = new ArrayList<>();
        amountTypeDto.add(new AmountTypeDto(1,"Kilogram", EntityStatus.ACTIVE));
        amountTypeDto.add(new AmountTypeDto(2,"Kilogram", EntityStatus.ACTIVE));

        when(amountTypeDao.findByEntityStatus(EntityStatus.ACTIVE)).thenReturn(amountTypeEntity);
        when(mapper.map(amountTypeEntity.get(0), AmountTypeDto.class)).thenReturn(amountTypeDto.get(0));
        when(mapper.map(amountTypeEntity.get(1), AmountTypeDto.class)).thenReturn(amountTypeDto.get(1));

        List<AmountTypeDto> amountTypeDtoList = amountTypeServiceImpl.getAll();

        assertEquals(amountTypeDto.get(0).getId(), amountTypeDtoList.get(0).getId());
        assertEquals(amountTypeDto.get(0).getType(), amountTypeDtoList.get(0).getType());
        assertEquals(amountTypeDto.get(1).getId(), amountTypeDtoList.get(1).getId());
        assertEquals(amountTypeDto.get(1).getType(), amountTypeDtoList.get(1).getType());

    }

    @Test
    public void testAdd() {
        AmountTypeDto amountTypeDto = new AmountTypeDto(1,"Kilogram", EntityStatus.ACTIVE);
        AmountTypeEntity amountTypeEntity = new AmountTypeEntity(1,"Kilogram", EntityStatus.ACTIVE);

        when(mapper.map(amountTypeDto, AmountTypeEntity.class)).thenReturn(amountTypeEntity);
        ///doNothing().doThrow(RuntimeException.class).when(amountTypeDao).save(amountTypeEntity);

        amountTypeServiceImpl.add(amountTypeDto);

        Mockito.verify(amountTypeDao, Mockito.times(1)).save(amountTypeEntity);
    }

    @Test
    public void testActivateCategory() {
        AmountTypeEntity amountTypeEntity = new AmountTypeEntity();
        amountTypeEntity.setId(3);

        ///doNothing().doThrow(RuntimeException.class).when(categoryDao).save(categoryEntity);

        ///when(this.mapper.map(categoryDto,CategoryEntity.class)).thenReturn(categoryEntity);

        amountTypeServiceImpl.activateAmountType(amountTypeEntity.getId());

        Mockito.verify(amountTypeDao, Mockito.times(1)).activateAmountType(amountTypeEntity.getId());
    }

    @Test
    public void testDeleteCategory() {
        AmountTypeEntity amountTypeEntity = new AmountTypeEntity();
        amountTypeEntity.setId(3);

        ///doNothing().doThrow(RuntimeException.class).when(categoryDao).save(categoryEntity);

        ///when(this.mapper.map(categoryDto,CategoryEntity.class)).thenReturn(categoryEntity);

        amountTypeServiceImpl.deleteAmountType(amountTypeEntity.getId());

        Mockito.verify(amountTypeDao, Mockito.times(1)).deleteAmountType(amountTypeEntity.getId());
    }

    @Test
    public void testDisableCategory() {
        AmountTypeEntity amountTypeEntity = new AmountTypeEntity();
        amountTypeEntity.setId(3);

        ///doNothing().doThrow(RuntimeException.class).when(categoryDao).save(categoryEntity);

        ///when(this.mapper.map(categoryDto,CategoryEntity.class)).thenReturn(categoryEntity);

        amountTypeServiceImpl.disableAmountType(amountTypeEntity.getId());

        Mockito.verify(amountTypeDao, Mockito.times(1)).disableAmountType(amountTypeEntity.getId());
    }

    @Test
    public void testGetById() {
        Integer id = 3;

        AmountTypeEntity amountTypeEntity = new AmountTypeEntity();
        amountTypeEntity.setId(3);
        amountTypeEntity.setEntityStatus(EntityStatus.ACTIVE);
        amountTypeEntity.setType("Kilogram");

        AmountTypeDto amountTypeDto = new AmountTypeDto();
        amountTypeDto.setType(amountTypeEntity.getType());
        amountTypeDto.setStatus(amountTypeEntity.getEntityStatus());
        amountTypeDto.setId(amountTypeEntity.getId());

        when(this.amountTypeDao.getById(id)).thenReturn(amountTypeEntity);
        when(this.mapper.map(amountTypeEntity,AmountTypeDto.class)).thenReturn(amountTypeDto);
        AmountTypeDto amountTypeDto1 = amountTypeServiceImpl.getById(amountTypeEntity.getId());

        assertEquals(amountTypeDto1.getId(), amountTypeDto.getId());
    }

    @Test
    public void testGetByType() {
        String name = "Kilogram";

        AmountTypeEntity amountTypeEntity = new AmountTypeEntity();
        amountTypeEntity.setId(3);
        amountTypeEntity.setEntityStatus(EntityStatus.ACTIVE);
        amountTypeEntity.setType("Kilogram");

        AmountTypeDto amountTypeDto = new AmountTypeDto();
        amountTypeDto.setType(amountTypeEntity.getType());
        amountTypeDto.setStatus(amountTypeEntity.getEntityStatus());
        amountTypeDto.setId(amountTypeEntity.getId());

        when(this.amountTypeDao.getByType(name)).thenReturn(amountTypeEntity);
        when(this.mapper.map(amountTypeEntity,AmountTypeDto.class)).thenReturn(amountTypeDto);
        AmountTypeDto amountTypeDto1 = amountTypeServiceImpl.getByType(amountTypeEntity.getType());

        assertEquals(amountTypeDto1.getType(), amountTypeDto.getType());
    }
}
