package net.productsellint.api.controllers;

import net.productsellint.business.abstracts.ProductService;
import net.productsellint.core.utilities.results.DataResult;
import net.productsellint.core.utilities.results.Result;
import net.productsellint.dataTransferObjects.concretes.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
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
    DataResult<List<ProductDto>> getAll() {
        return this.productService.getAll();
    }

    @GetMapping("/getAllByPage")
    DataResult<List<ProductDto>> getAll(@RequestParam int pageNo, @RequestParam int pageSize) {
        return this.productService.getAll(pageNo, pageSize);
    }

    @GetMapping("/getAllSorted")
    DataResult<List<ProductDto>> getAllSorted() {
        return this.productService.getAllSorted();
    }

    @GetMapping("/addProduct")
    Result add(@RequestParam ProductDto productDto) {
        return this.productService.add(productDto);
    }

    @GetMapping("/dropProduct")
    Result drop(@RequestParam Integer id) {
        return this.productService.drop(id);
    }

    @GetMapping("/getByProductName")
    DataResult<ProductDto> getByProductName(String productName) {
        return this.productService.getByProductName(productName);
    }
}
