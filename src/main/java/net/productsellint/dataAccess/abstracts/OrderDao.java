package net.productsellint.dataAccess.abstracts;

import net.productsellint.entities.concretes.DeliveryStatus;
import net.productsellint.entities.concretes.EntityStatus;
import net.productsellint.entities.concretes.OrderEntity;
import net.productsellint.entities.concretes.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDao extends JpaRepository<OrderEntity, Integer> {
    OrderEntity findByUuid(String uuid);

    List<OrderEntity> findByEntityStatus(EntityStatus entityStatus, Sort sort);
    List<OrderEntity> findByEntityStatus(EntityStatus entityStatus, Pageable pageable);
    List<OrderEntity> findByEntityStatus(EntityStatus entityStatus);

    List<OrderEntity> findByDeliveryStatus(DeliveryStatus deliveryStatus, Sort sort);
    List<OrderEntity> findByDeliveryStatus(DeliveryStatus deliveryStatus, Pageable pageable);
    List<OrderEntity> findByDeliveryStatus(DeliveryStatus deliveryStatus);

    @Modifying
    @Query("update OrderEntity o set o.deliveryStatus = 0 where o.id = :id")
    void setOrderToInProcess(@Param("id") Integer id);
    @Modifying
    @Query("update OrderEntity o set o.deliveryStatus = 1 where o.id = :id")
    void setOrderToOnTheWay(@Param("id") Integer id);
    @Modifying
    @Query("update OrderEntity o set o.deliveryStatus = 2 where o.id = :id")
    void setOrderToDelivered(@Param("id") Integer id);

    @Modifying
    @Query("update OrderEntity o set o.entityStatus = 1 where o.id = :id")
    void deleteOrder(@Param("id") Integer id);
    @Modifying
    @Query("update OrderEntity o set o.entityStatus = 2 where o.id = :id")
    void disableOrder(@Param("id") Integer id);
    @Modifying
    @Query("update OrderEntity o set o.entityStatus = 0 where o.id = :id")
    void activateOrder(@Param("id") Integer id);
}
