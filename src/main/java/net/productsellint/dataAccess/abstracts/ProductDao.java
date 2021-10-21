package net.productsellint.dataAccess.abstracts;

import net.productsellint.entities.concretes.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<ProductEntity, Integer> {
    ProductEntity getByProductName(String productName);
}
