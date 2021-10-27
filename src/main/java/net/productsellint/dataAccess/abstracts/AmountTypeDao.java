package net.productsellint.dataAccess.abstracts;

import net.productsellint.entities.concretes.AmountTypeEntity;
import net.productsellint.entities.concretes.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AmountTypeDao extends JpaRepository<AmountTypeEntity, Integer> {
    AmountTypeEntity getById(Integer id);
    AmountTypeEntity getByType(String categoryName);
    List<AmountTypeEntity> findByEntityStatus(EntityStatus entityStatus);

    @Modifying
    @Query("update AmountTypeEntity a set a.entityStatus = 1 where a.id = :id")
    void deleteAmountType(@Param("id") Integer id);

    @Modifying
    @Query("update AmountTypeEntity a set a.entityStatus = 2 where a.id = :id")
    void disableAmountType(@Param("id") Integer id);

    @Modifying
    @Query("update AmountTypeEntity a set a.entityStatus = 0 where a.id = :id")
    void activateAmountType(@Param("id") Integer id);
}
