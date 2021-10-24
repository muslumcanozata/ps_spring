package net.productsellint.api.controllers;

import net.productsellint.business.abstracts.ProductService;
import net.productsellint.core.utilities.results.DataResult;
import net.productsellint.core.utilities.results.Result;
import net.productsellint.dataTransferObjects.concretes.ProductDto;
import net.productsellint.entities.concretes.EntityStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductsController {
    private final ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        super();
        this.productService = productService;
    }

    @GetMapping("/getAll")
    ResponseEntity<DataResult<List<ProductDto>>> getAll() {
        return this.productService.getAll();
    }

    @GetMapping("/getAllByPage")
    ResponseEntity<DataResult<List<ProductDto>>> getAll(@RequestParam int pageNo, @RequestParam int pageSize) {
        return this.productService.getAll(pageNo, pageSize);
    }

    @GetMapping("/getAllSorted")
    ResponseEntity<DataResult<List<ProductDto>>> getAllSorted() {
        return this.productService.getAllSorted();
    }

    @GetMapping("/addProduct")
    ResponseEntity<Result> add(@RequestParam ProductDto productDto) {
        return this.productService.add(productDto);
    }

    @GetMapping("/deleteProduct")
    ResponseEntity<Result> deleteProduct(@RequestParam Integer id) {
        return this.productService.deleteProduct(id);
    }

    @GetMapping("/activateProduct")
    ResponseEntity<Result> activateProduct(@RequestParam Integer id) {
        return this.productService.activateProduct(id);
    }

    @GetMapping("/disableProduct")
    ResponseEntity<Result> disableProduct(@RequestParam Integer id) {
        return this.productService.disableProduct(id);
    }

    @GetMapping("/getByProductName")
    ResponseEntity<DataResult<ProductDto>> getByProductNameAndEntityStatus(@RequestParam String productName, @RequestParam EntityStatus entityStatus) {
        return this.productService.getByProductNameAndEntityStatus(productName, entityStatus);
    }
}
