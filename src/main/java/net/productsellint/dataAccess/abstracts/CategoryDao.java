package net.productsellint.dataAccess.abstracts;

import net.productsellint.entities.concretes.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<CategoryEntity, Integer> {
    CategoryEntity getById(Integer id);
    CategoryEntity getByCategoryName(String categoryName);
}
