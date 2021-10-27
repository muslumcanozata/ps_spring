package net.productsellint.dataAccess.abstracts;

import net.productsellint.entities.concretes.EntityStatus;
import net.productsellint.entities.concretes.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDao extends JpaRepository<ProductEntity, Integer> {
    ProductEntity getByProductNameAndEntityStatus(String productName, EntityStatus entityStatus);
    List<ProductEntity> findByEntityStatus(EntityStatus entityStatus, Sort sort);
    List<ProductEntity> findByEntityStatus(EntityStatus entityStatus, Pageable pageable);
    List<ProductEntity> findByEntityStatus(EntityStatus entityStatus);

    @Modifying
    @Query("update ProductEntity p set p.entityStatus = 1 where p.id = :id")
    void deleteProduct(@Param("id") Integer id);

    @Modifying
    @Query("update ProductEntity p set p.entityStatus = 2 where p.id = :id")
    void disableProduct(@Param("id") Integer id);

    @Modifying
    @Query("update ProductEntity p set p.entityStatus = 0 where p.id = :id")
    void activateProduct(@Param("id") Integer id);
}
