package net.productsellint.business.concretes;

import net.productsellint.business.abstracts.ProductService;
import net.productsellint.core.utilities.results.*;
import net.productsellint.dataAccess.abstracts.ProductDao;
import net.productsellint.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager implements ProductService {
    private ProductDao productDao;

    @Autowired
    public ProductManager(ProductDao productDao) {
        super();
        this.productDao = productDao;
    }

    @Override
    public DataResult<List<Product>> getAll() {
        return new SuccessDataResult<List<Product>>(this.productDao.findAll(), "Veri Listelendi.");
    }

    @Override
    public DataResult<List<Product>> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return new SuccessDataResult<List<Product>>(this.productDao.findAll(pageable).getContent(), "Veri Listelendi.");
    }

    @Override
    public DataResult<List<Product>> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.ASC, "productName");
        return new SuccessDataResult<List<Product>>(this.productDao.findAll(sort), "Data Listelendi.");
    }

    @Override
    public Result add(Product product) {
        try{
            this.productDao.save(product);
            return new SuccessResult("Ürün eklendi.");
        } catch (Exception e) {
            return new ErrorResult(e.toString());
        }
    }

    @Override
    public Result drop(Integer id) {
        try{
            this.productDao.deleteById(id);
            return new SuccessResult("Ürün silindi.");
        } catch (Exception e) {
            return new ErrorResult(e.toString());
        }
    }

    @Override
    public DataResult<Product> getByProductName(String productName) {
        return new SuccessDataResult<Product>(this.productDao.getByProductName(productName));
    }
}
