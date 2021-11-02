package net.productsellint.servicesTest;

import net.productsellint.business.concretes.OrderServiceImpl;
import net.productsellint.dataAccess.abstracts.OrderDao;
import net.productsellint.dataTransferObjects.concretes.OrderDto;
import net.productsellint.entities.concretes.DeliveryStatus;
import net.productsellint.entities.concretes.EntityStatus;
import net.productsellint.entities.concretes.OrderEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
public class OrderServiceTest {
    @Mock
    private OrderDao orderDao;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    OrderServiceImpl orderService;

    @Test
    public void testGetAll() {
        List<OrderEntity> orderEntities = new ArrayList<>();
        orderEntities.add(new OrderEntity());
        orderEntities.add(new OrderEntity());
        orderEntities.get(0).setId(1);
        orderEntities.get(0).setUuid(UUID.randomUUID());
        orderEntities.get(1).setId(2);
        orderEntities.get(1).setUuid(UUID.randomUUID());

        List<OrderDto> orderDtos = new ArrayList<>();
        orderDtos.add(new OrderDto());
        orderDtos.add(new OrderDto());
        orderDtos.get(0).setId(1);
        orderDtos.get(0).setUuid(orderEntities.get(0).getUuid());
        orderDtos.get(1).setId(2);
        orderDtos.get(1).setUuid(orderEntities.get(0).getUuid());

        when(orderDao.findByEntityStatus(EntityStatus.ACTIVE)).thenReturn(orderEntities);
        when(mapper.map(orderEntities.get(0), OrderDto.class)).thenReturn(orderDtos.get(0));
        when(mapper.map(orderEntities.get(1), OrderDto.class)).thenReturn(orderDtos.get(1));


        List<OrderDto> orderDtoList = orderService.getAll();

        assertEquals(orderDtos.get(0).getId(), orderDtoList.get(0).getId());
        assertEquals(orderDtos.get(0).getUuid(), orderDtoList.get(0).getUuid());
        assertEquals(orderDtos.get(1).getId(), orderDtoList.get(1).getId());
        assertEquals(orderDtos.get(1).getUuid(), orderDtoList.get(1).getUuid());
    }

    @Test
    public void testGetAllPageable() {
        int pageNo = 1;
        int pageSize = 2;
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);

        List<OrderEntity> orderEntities = new ArrayList<>();
        orderEntities.add(new OrderEntity());
        orderEntities.add(new OrderEntity());
        orderEntities.get(0).setId(1);
        orderEntities.get(0).setUuid(UUID.randomUUID());
        orderEntities.get(1).setId(2);
        orderEntities.get(1).setUuid(UUID.randomUUID());

        List<OrderDto> orderDtos = new ArrayList<>();
        orderDtos.add(new OrderDto());
        orderDtos.add(new OrderDto());
        orderDtos.get(0).setId(1);
        orderDtos.get(0).setUuid(orderEntities.get(0).getUuid());
        orderDtos.get(1).setId(2);
        orderDtos.get(1).setUuid(orderEntities.get(0).getUuid());

        when(orderDao.findByEntityStatus(EntityStatus.ACTIVE, pageable)).thenReturn(orderEntities);
        when(mapper.map(orderEntities.get(0), OrderDto.class)).thenReturn(orderDtos.get(0));
        when(mapper.map(orderEntities.get(1), OrderDto.class)).thenReturn(orderDtos.get(1));


        List<OrderDto> orderDtoList = orderService.getAll(pageNo, pageSize);

        assertEquals(orderDtos.get(0).getId(), orderDtoList.get(0).getId());
        assertEquals(orderDtos.get(0).getUuid(), orderDtoList.get(0).getUuid());
        assertEquals(orderDtos.get(1).getId(), orderDtoList.get(1).getId());
        assertEquals(orderDtos.get(1).getUuid(), orderDtoList.get(1).getUuid());
    }

    @Test
    public void testGetAllSorted() {
        Sort sort = Sort.by(Sort.Direction.ASC, "uuid");

        List<OrderEntity> orderEntities = new ArrayList<>();
        orderEntities.add(new OrderEntity());
        orderEntities.add(new OrderEntity());
        orderEntities.get(0).setId(1);
        orderEntities.get(0).setUuid(UUID.randomUUID());
        orderEntities.get(1).setId(2);
        orderEntities.get(1).setUuid(UUID.randomUUID());

        List<OrderDto> orderDtos = new ArrayList<>();
        orderDtos.add(new OrderDto());
        orderDtos.add(new OrderDto());
        orderDtos.get(0).setId(1);
        orderDtos.get(0).setUuid(orderEntities.get(0).getUuid());
        orderDtos.get(1).setId(2);
        orderDtos.get(1).setUuid(orderEntities.get(0).getUuid());

        when(orderDao.findByEntityStatus(EntityStatus.ACTIVE, sort)).thenReturn(orderEntities);
        when(mapper.map(orderEntities.get(0), OrderDto.class)).thenReturn(orderDtos.get(0));
        when(mapper.map(orderEntities.get(1), OrderDto.class)).thenReturn(orderDtos.get(1));


        List<OrderDto> orderDtoList = orderService.getAllSorted();

        assertEquals(orderDtos.get(0).getId(), orderDtoList.get(0).getId());
        assertEquals(orderDtos.get(0).getUuid(), orderDtoList.get(0).getUuid());
        assertEquals(orderDtos.get(1).getId(), orderDtoList.get(1).getId());
        assertEquals(orderDtos.get(1).getUuid(), orderDtoList.get(1).getUuid());
    }

    @Test
    public void testfindByDeliveryStatusAndPage() {
        int pageNo = 1;
        int pageSize = 2;
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        DeliveryStatus deliveryStatus = DeliveryStatus.DELIVERED;

        List<OrderEntity> orderEntities = new ArrayList<>();
        orderEntities.add(new OrderEntity());
        orderEntities.add(new OrderEntity());
        orderEntities.get(0).setId(1);
        orderEntities.get(0).setUuid(UUID.randomUUID());
        orderEntities.get(1).setId(2);
        orderEntities.get(1).setUuid(UUID.randomUUID());

        List<OrderDto> orderDtos = new ArrayList<>();
        orderDtos.add(new OrderDto());
        orderDtos.add(new OrderDto());
        orderDtos.get(0).setId(1);
        orderDtos.get(0).setUuid(orderEntities.get(0).getUuid());
        orderDtos.get(1).setId(2);
        orderDtos.get(1).setUuid(orderEntities.get(0).getUuid());

        when(this.orderDao.findByDeliveryStatus(deliveryStatus, pageable)).thenReturn(orderEntities);
        when(mapper.map(orderEntities.get(0), OrderDto.class)).thenReturn(orderDtos.get(0));
        when(mapper.map(orderEntities.get(1), OrderDto.class)).thenReturn(orderDtos.get(1));


        List<OrderDto> orderDtoList = orderService.findByDeliveryStatusAndPage(deliveryStatus, pageNo, pageSize);

        assertEquals(orderDtos.get(0).getId(), orderDtoList.get(0).getId());
        assertEquals(orderDtos.get(0).getUuid(), orderDtoList.get(0).getUuid());
        assertEquals(orderDtos.get(1).getId(), orderDtoList.get(1).getId());
        assertEquals(orderDtos.get(1).getUuid(), orderDtoList.get(1).getUuid());
    }

    public void testfindByDeliveryStatusAndSort() {
        Sort sort = Sort.by(Sort.Direction.ASC, "uuid");

        DeliveryStatus deliveryStatus = DeliveryStatus.DELIVERED;

        List<OrderEntity> orderEntities = new ArrayList<>();
        orderEntities.add(new OrderEntity());
        orderEntities.add(new OrderEntity());
        orderEntities.get(0).setId(1);
        orderEntities.get(0).setUuid(UUID.randomUUID());
        orderEntities.get(1).setId(2);
        orderEntities.get(1).setUuid(UUID.randomUUID());

        List<OrderDto> orderDtos = new ArrayList<>();
        orderDtos.add(new OrderDto());
        orderDtos.add(new OrderDto());
        orderDtos.get(0).setId(1);
        orderDtos.get(0).setUuid(orderEntities.get(0).getUuid());
        orderDtos.get(1).setId(2);
        orderDtos.get(1).setUuid(orderEntities.get(0).getUuid());

        when(this.orderDao.findByDeliveryStatus(deliveryStatus, sort)).thenReturn(orderEntities);
        when(mapper.map(orderEntities.get(0), OrderDto.class)).thenReturn(orderDtos.get(0));
        when(mapper.map(orderEntities.get(1), OrderDto.class)).thenReturn(orderDtos.get(1));


        List<OrderDto> orderDtoList = orderService.findByDeliveryStatusAndSort(deliveryStatus);

        assertEquals(orderDtos.get(0).getId(), orderDtoList.get(0).getId());
        assertEquals(orderDtos.get(0).getUuid(), orderDtoList.get(0).getUuid());
        assertEquals(orderDtos.get(1).getId(), orderDtoList.get(1).getId());
        assertEquals(orderDtos.get(1).getUuid(), orderDtoList.get(1).getUuid());
    }


}
