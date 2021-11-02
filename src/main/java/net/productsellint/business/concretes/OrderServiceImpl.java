package net.productsellint.business.concretes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import net.productsellint.business.abstracts.OrderService;
import net.productsellint.dataAccess.abstracts.OrderDao;
import net.productsellint.dataAccess.abstracts.ProductDao;
import net.productsellint.dataTransferObjects.concretes.OrderDto;
import net.productsellint.dataTransferObjects.concretes.OrderRequest;
import net.productsellint.dataTransferObjects.concretes.OrderSingleRequest;
import net.productsellint.entities.concretes.DeliveryStatus;
import net.productsellint.entities.concretes.EntityStatus;
import net.productsellint.entities.concretes.OrderEntity;
import net.productsellint.exception.custom.OrderNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final ProductDao productDao;
    private final ModelMapper modelMapper;
    private final Gson gsonBuilder;

    public OrderServiceImpl(OrderDao orderDao, ProductDao productDao, ModelMapper modelMapper, Gson gsonBuilder) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.modelMapper = modelMapper;
        this.gsonBuilder = gsonBuilder;
    }

    public List<OrderDto> findByDeliveryStatusAndPage(DeliveryStatus deliveryStatus, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return this.orderDao.findByDeliveryStatus(deliveryStatus, pageable)
                .stream()
                .map(orderEntity -> modelMapper.map(orderEntity, OrderDto.class))
                .collect(Collectors.toList());
    }

    public List<OrderDto> findByDeliveryStatusAndSort(DeliveryStatus deliveryStatus) {
        Sort sort = Sort.by(Sort.Direction.ASC, "uuid");
        return this.orderDao.findByDeliveryStatus(deliveryStatus, sort)
                .stream()
                .map(orderEntity -> modelMapper.map(orderEntity, OrderDto.class))
                .collect(Collectors.toList());
    }

    public List<OrderDto> findByDeliveryStatus(DeliveryStatus deliveryStatus) {
        return this.orderDao.findByDeliveryStatus(deliveryStatus)
                .stream()
                .map(orderEntity -> modelMapper.map(orderEntity, OrderDto.class))
                .collect(Collectors.toList());
    }

    public List<OrderDto> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return this.orderDao.findByEntityStatus(EntityStatus.ACTIVE, pageable)
                .stream()
                .map(orderEntity -> modelMapper.map(orderEntity, OrderDto.class))
                .collect(Collectors.toList());
    }

    public List<OrderDto> getAll() {
        return this.orderDao.findByEntityStatus(EntityStatus.ACTIVE)
                .stream()
                .map(orderEntity -> modelMapper.map(orderEntity, OrderDto.class))
                .collect(Collectors.toList());
    }

    public List<OrderDto> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.ASC, "uuid");
        return this.orderDao.findByEntityStatus(EntityStatus.ACTIVE, sort)
                .stream()
                .map(orderEntity -> modelMapper.map(orderEntity, OrderDto.class))
                .collect(Collectors.toList());
    }

    public OrderDto getByUUID(String uuid) {
        OrderEntity orderEntity =  this.orderDao.findByUuid(uuid);
        return modelMapper.map(orderEntity, OrderDto.class);
    }

    public void setOrderToInProcess(Integer id) {
        this.orderDao.setOrderToInProcess(id);
    }

    public void setOrderToOnTheWay(Integer id) {
        this.orderDao.setOrderToOnTheWay(id);
    }

    public void setOrderToDelivered(Integer id) {
        this.orderDao.setOrderToDelivered(id);
    }

    @Transactional
    public void add(OrderRequest orderRequest) {
        String orderList = gsonBuilder.toJson(orderRequest.getOrderSingleRequestList());
        OrderEntity orderEntity = new OrderEntity(
                orderList,
                orderRequest.getDeliveryForecast(),
                orderRequest.getUserId(),
                orderRequest.getDeliveryStatus(),
                orderRequest.getEntityStatus()
        );
        for(OrderSingleRequest orderSingleRequest : orderRequest.getOrderSingleRequestList()) {
            this.productDao.decreaseStock(orderSingleRequest.getProductId(), orderSingleRequest.getAmount());
        }
        this.orderDao.save(orderEntity);
    }

    @Transactional
    public void delete(Integer id) {
        OrderEntity orderEntity = this.orderDao.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        List<OrderSingleRequest> orderSingleRequestList = Arrays.asList(new Gson().fromJson(orderEntity.getOrderList(), OrderSingleRequest[].class));
        for(OrderSingleRequest orderSingleRequest : orderSingleRequestList) {
            this.productDao.decreaseStock(orderSingleRequest.getProductId(), orderSingleRequest.getAmount());
        }
        this.orderDao.deleteOrder(id);
    }

    public void disable(Integer id) {
        this.orderDao.disableOrder(id);
    }

    public void activate(Integer id) {
        this.orderDao.activateOrder(id);
    }
}
