package net.productsellint.business.abstracts;

import net.productsellint.core.utilities.results.DataResult;
import net.productsellint.core.utilities.results.Result;
import net.productsellint.entities.concretes.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    DataResult<List<Product>> getAll();
    DataResult<List<Product>> getAll(int pageNo, int pageSize);
    DataResult<List<Product>> getAllSorted();

    Result add(Product product);

    Result drop(Integer id);

    DataResult<Product> getByProductName(String productName);


}
