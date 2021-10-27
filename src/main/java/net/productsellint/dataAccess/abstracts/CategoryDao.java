package net.productsellint.dataAccess.abstracts;

import net.productsellint.entities.concretes.CategoryEntity;
import net.productsellint.entities.concretes.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryDao extends JpaRepository<CategoryEntity, Integer> {
    CategoryEntity getById(Integer id);
    CategoryEntity getByCategoryName(String categoryName);
    List<CategoryEntity> findByEntityStatus(EntityStatus entityStatus);

    @Modifying
    @Query("update CategoryEntity c set c.entityStatus = 1 where c.id = :id")
    void deleteCategory(@Param("id") Integer id);

    @Modifying
    @Query("update CategoryEntity c set c.entityStatus = 2 where c.id = :id")
    void disableCategory(@Param("id") Integer id);

    @Modifying
    @Query("update CategoryEntity c set c.entityStatus = 0 where c.id = :id")
    void activateCategory(@Param("id") Integer id);
}
