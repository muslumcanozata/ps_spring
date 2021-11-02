package net.productsellint.business.abstracts;

import net.productsellint.dataTransferObjects.concretes.ProductDto;
import net.productsellint.dataTransferObjects.concretes.ProductRequest;
import net.productsellint.entities.concretes.EntityStatus;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();
    List<ProductDto> getAll(int pageNo, int pageSize);
    List<ProductDto> getAllSorted();

    void add(ProductRequest productRequest);

    void deleteProduct(Integer id);
    void activateProduct(Integer id);
    void disableProduct(Integer id);

    ProductDto getByProductNameAndEntityStatus(String productName, EntityStatus entityStatus);
}
