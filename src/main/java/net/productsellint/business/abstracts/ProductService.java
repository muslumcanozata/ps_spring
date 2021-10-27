package net.productsellint.business.abstracts;

import net.productsellint.core.utilities.results.DataResult;
import net.productsellint.core.utilities.results.Result;
import net.productsellint.dataTransferObjects.concretes.ProductDto;
import net.productsellint.entities.concretes.EntityStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<DataResult<List<ProductDto>>> getAll();
    ResponseEntity<DataResult<List<ProductDto>>> getAll(int pageNo, int pageSize);
    ResponseEntity<DataResult<List<ProductDto>>> getAllSorted();

    ResponseEntity<Result> add(ProductDto productDto);

    ResponseEntity<Result> deleteProduct(Integer id);
    ResponseEntity<Result> activateProduct(Integer id);
    ResponseEntity<Result> disableProduct(Integer id);

    ProductDto getByProductNameAndEntityStatus(String productName, EntityStatus entityStatus);
}
