package net.productsellint.dataAccess.abstracts;

import net.productsellint.entities.concretes.EntityStatus;
import net.productsellint.entities.concretes.ProductEntity;
import net.productsellint.entities.concretes.StockEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockDao extends JpaRepository<StockEntity, Integer> {
    List<StockEntity> findByEntityStatus(EntityStatus entityStatus, Sort sort);
    List<StockEntity> findByEntityStatus(EntityStatus entityStatus, Pageable pageable);
    List<StockEntity> findByEntityStatus(EntityStatus entityStatus);

    @Modifying
    @Query("update StockEntity s set s.entityStatus = 1 where s.id = :id")
    void deleteStock(@Param("id") Integer id);

    @Modifying
    @Query("update StockEntity s set s.entityStatus = 2 where s.id = :id")
    void disableStock(@Param("id") Integer id);

    @Modifying
    @Query("update StockEntity s set s.entityStatus = 0 where s.id = :id")
    void activateStock(@Param("id") Integer id);

    @Modifying
    @Query("update StockEntity s set s.stock = s.stock + :amount where s.productEntity.id = :id")
    void increaseStock(@Param("id") Integer id, @Param("amount") Float amount);

    @Modifying
    @Query("update StockEntity s set s.stock = s.stock - :amount where s.productEntity.id = :id")
    void decreaseStock(@Param("id") Integer id, @Param("amount") Float amount);
}
