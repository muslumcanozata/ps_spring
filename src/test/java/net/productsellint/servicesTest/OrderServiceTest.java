package net.productsellint.servicesTest;

import com.google.gson.GsonBuilder;
import net.productsellint.business.concretes.OrderServiceImpl;
import net.productsellint.business.concretes.StockServiceImpl;
import net.productsellint.dataAccess.abstracts.OrderDao;
import net.productsellint.dataTransferObjects.concretes.OrderDto;
import net.productsellint.dataTransferObjects.concretes.OrderRequest;
import net.productsellint.dataTransferObjects.concretes.OrderSingleRequest;
import net.productsellint.entities.concretes.DeliveryStatus;
import net.productsellint.entities.concretes.EntityStatus;
import net.productsellint.entities.concretes.OrderEntity;
import net.productsellint.entities.concretes.UserEntity;
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
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
public class OrderServiceTest {
    @Mock
    private OrderDao orderDao;

    @Mock
    private StockServiceImpl stockServiceImpl;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    OrderServiceImpl orderServiceImpl;

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


        List<OrderDto> orderDtoList = orderServiceImpl.getAll();

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


        List<OrderDto> orderDtoList = orderServiceImpl.getAll(pageNo, pageSize);

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


        List<OrderDto> orderDtoList = orderServiceImpl.getAllSorted();

        assertEquals(orderDtos.get(0).getId(), orderDtoList.get(0).getId());
        assertEquals(orderDtos.get(0).getUuid(), orderDtoList.get(0).getUuid());
        assertEquals(orderDtos.get(1).getId(), orderDtoList.get(1).getId());
        assertEquals(orderDtos.get(1).getUuid(), orderDtoList.get(1).getUuid());
    }

    @Test
    public void testFindByDeliveryStatusAndPage() {
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


        List<OrderDto> orderDtoList = orderServiceImpl.findByDeliveryStatusAndPage(deliveryStatus, pageNo, pageSize);

        assertEquals(orderDtos.get(0).getId(), orderDtoList.get(0).getId());
        assertEquals(orderDtos.get(0).getUuid(), orderDtoList.get(0).getUuid());
        assertEquals(orderDtos.get(1).getId(), orderDtoList.get(1).getId());
        assertEquals(orderDtos.get(1).getUuid(), orderDtoList.get(1).getUuid());
    }

    @Test
    public void testFindByDeliveryStatusAndSort() {
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


        List<OrderDto> orderDtoList = orderServiceImpl.findByDeliveryStatusAndSort(deliveryStatus);

        assertEquals(orderDtos.get(0).getId(), orderDtoList.get(0).getId());
        assertEquals(orderDtos.get(0).getUuid(), orderDtoList.get(0).getUuid());
        assertEquals(orderDtos.get(1).getId(), orderDtoList.get(1).getId());
        assertEquals(orderDtos.get(1).getUuid(), orderDtoList.get(1).getUuid());
    }

    @Test
    public void testFindByDeliveryStatus() {
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

        when(this.orderDao.findByDeliveryStatus(deliveryStatus)).thenReturn(orderEntities);
        when(mapper.map(orderEntities.get(0), OrderDto.class)).thenReturn(orderDtos.get(0));
        when(mapper.map(orderEntities.get(1), OrderDto.class)).thenReturn(orderDtos.get(1));

        List<OrderDto> orderDtoList = orderServiceImpl.findByDeliveryStatus(deliveryStatus);

        assertEquals(orderDtos.get(0).getId(), orderDtoList.get(0).getId());
        assertEquals(orderDtos.get(0).getUuid(), orderDtoList.get(0).getUuid());
        assertEquals(orderDtos.get(1).getId(), orderDtoList.get(1).getId());
        assertEquals(orderDtos.get(1).getUuid(), orderDtoList.get(1).getUuid());
    }

    @Test
    public void testGetByUUID() {
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

        when(this.orderDao.findByUuid(orderEntities.get(0).getUuid().toString())).thenReturn(orderEntities.get(0));
        when(this.orderDao.findByUuid(orderEntities.get(1).getUuid().toString())).thenReturn(orderEntities.get(1));
        when(mapper.map(orderEntities.get(0), OrderDto.class)).thenReturn(orderDtos.get(0));
        when(mapper.map(orderEntities.get(1), OrderDto.class)).thenReturn(orderDtos.get(1));

        OrderDto orderDto1 = orderServiceImpl.getByUUID(orderEntities.get(0).getUuid().toString());
        OrderDto orderDto2 = orderServiceImpl.getByUUID(orderEntities.get(1).getUuid().toString());

        assertEquals(orderDtos.get(0).getId(), orderDto1.getId());
        assertEquals(orderDtos.get(0).getUuid(), orderDto1.getUuid());
        assertEquals(orderDtos.get(1).getId(), orderDto2.getId());
        assertEquals(orderDtos.get(1).getUuid(), orderDto2.getUuid());
    }

    @Test
    public void testSetOrderToInProcess() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1);
        orderEntity.setUuid(UUID.randomUUID());
        orderEntity.setDeliveryStatus(DeliveryStatus.INPROCESS);

        orderServiceImpl.setOrderToInProcess(orderEntity.getId());

        Mockito.verify(orderDao, Mockito.times(1)).setOrderToInProcess(orderEntity.getId());
    }

    @Test
    public void testSetOrderToOnTheWay() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1);
        orderEntity.setUuid(UUID.randomUUID());
        orderEntity.setDeliveryStatus(DeliveryStatus.ONTHEWAY);

        orderServiceImpl.setOrderToOnTheWay(orderEntity.getId());

        Mockito.verify(orderDao, Mockito.times(1)).setOrderToOnTheWay(orderEntity.getId());
    }

    @Test
    public void testSetOrderToDelivered() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1);
        orderEntity.setUuid(UUID.randomUUID());
        orderEntity.setDeliveryStatus(DeliveryStatus.DELIVERED);

        orderServiceImpl.setOrderToDelivered(orderEntity.getId());

        Mockito.verify(orderDao, Mockito.times(1)).setOrderToDelivered(orderEntity.getId());
    }

    @Test
    public void testAdd() {
        List<OrderSingleRequest> orderSingleRequestList = new ArrayList<>();
        orderSingleRequestList.add(new OrderSingleRequest());
        orderSingleRequestList.add(new OrderSingleRequest());
        orderSingleRequestList.get(0).setProductId(1);
        orderSingleRequestList.get(0).setAmount(2.001f);
        orderSingleRequestList.get(1).setProductId(2);
        orderSingleRequestList.get(0).setAmount(2.002f);

        String orderList = new GsonBuilder().create().toJson(orderSingleRequestList);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderSingleRequestList(orderSingleRequestList);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserEntity(new UserEntity());
        orderEntity.setOrderList(orderList);

        orderServiceImpl.add(orderRequest);

        Mockito.verify(stockServiceImpl,Mockito.times(1)).decreaseStock(orderSingleRequestList.get(0).getProductId(), orderSingleRequestList.get(0).getAmount());
        Mockito.verify(stockServiceImpl, Mockito.times(1)).decreaseStock(orderSingleRequestList.get(1).getProductId(), orderSingleRequestList.get(1).getAmount());
        Mockito.verify(orderDao, Mockito.times(1)).save(orderEntity);
    }

    @Test
    public void testDelete() {
        List<OrderSingleRequest> orderSingleRequestList = new ArrayList<>();
        orderSingleRequestList.add(new OrderSingleRequest());
        orderSingleRequestList.add(new OrderSingleRequest());
        orderSingleRequestList.get(0).setProductId(1);
        orderSingleRequestList.get(0).setAmount(2.001f);
        orderSingleRequestList.get(1).setProductId(2);
        orderSingleRequestList.get(0).setAmount(2.002f);

        String orderList = new GsonBuilder().create().toJson(orderSingleRequestList);

        OrderDto orderDto = new OrderDto();
        orderDto.setOrderList(orderSingleRequestList);
        orderDto.setId(1);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderList(orderList);
        orderEntity.setId(1);

        when(orderDao.findById(orderDto.getId())).thenReturn(java.util.Optional.of(orderEntity));

        orderServiceImpl.delete(orderDto.getId());

        Mockito.verify(stockServiceImpl,Mockito.times(1)).increaseStock(orderSingleRequestList.get(0).getProductId(), orderSingleRequestList.get(0).getAmount());
        Mockito.verify(stockServiceImpl, Mockito.times(1)).increaseStock(orderSingleRequestList.get(1).getProductId(), orderSingleRequestList.get(1).getAmount());
        Mockito.verify(orderDao, Mockito.times(1)).deleteOrder(orderDto.getId());
    }

    @Test
    public void testDisable() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1);
        orderEntity.setEntityStatus(EntityStatus.DELETED);

        orderServiceImpl.disable(orderEntity.getId());

        Mockito.verify(orderDao, Mockito.times(1)).disableOrder(orderEntity.getId());
    }

    @Test
    public void testActivate() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1);
        orderEntity.setEntityStatus(EntityStatus.DELETED);

        orderServiceImpl.activate(orderEntity.getId());

        Mockito.verify(orderDao, Mockito.times(1)).activateOrder(orderEntity.getId());
    }
}
