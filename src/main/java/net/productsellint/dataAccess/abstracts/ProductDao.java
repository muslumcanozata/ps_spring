package net.productsellint.dataAccess.abstracts;

import net.productsellint.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Integer> {
    Product getByProductName(String productName);
}
