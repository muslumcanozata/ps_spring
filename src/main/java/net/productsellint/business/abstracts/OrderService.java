package net.productsellint.business.abstracts;

import net.productsellint.dataTransferObjects.concretes.OrderDto;
import net.productsellint.dataTransferObjects.concretes.OrderRequest;
import net.productsellint.entities.concretes.DeliveryStatus;
import java.util.List;

public interface OrderService {
    List<OrderDto> findByDeliveryStatusAndPage(DeliveryStatus deliveryStatus, int pageNo, int pageSize);
    List<OrderDto> findByDeliveryStatusAndSort(DeliveryStatus deliveryStatus);
    List<OrderDto> findByDeliveryStatus(DeliveryStatus deliveryStatus);

    List<OrderDto> getAll(int pageNo, int pageSize);
    List<OrderDto> getAll();
    List<OrderDto> getAllSorted();

    OrderDto getByUUID(String uuid);

    void setOrderToInProcess(Integer id);
    void setOrderToOnTheWay(Integer id);
    void setOrderToDelivered(Integer id);

    void add(OrderRequest orderDto);

    void delete(Integer id);
    void disable(Integer id);
    void activate(Integer id);
}