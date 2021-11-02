package net.productsellint.api.controllers;

import net.productsellint.business.concretes.ProductServiceImpl;
import net.productsellint.core.utilities.results.SuccessResult;
import net.productsellint.dataTransferObjects.concretes.ProductDto;
import net.productsellint.dataTransferObjects.concretes.ProductRequest;
import net.productsellint.entities.concretes.EntityStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/products")
public class ProductsController {
    private final ProductServiceImpl productServiceImpl;

    @Autowired
    public ProductsController(ProductServiceImpl productServiceImpl) {
        super();
        this.productServiceImpl = productServiceImpl;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll() {
        List<ProductDto> productDtoList = this.productServiceImpl.getAll();

        Map<String, Object> map = new HashMap();
        map.put("products", productDtoList);
        map.put("size", productDtoList.size());

        return ResponseEntity.status(200).body(map);
    }

    @GetMapping("/getAllByPage")
    ResponseEntity<List<ProductDto>> getAll(@RequestParam int pageNo, @RequestParam int pageSize) {
        return ResponseEntity.status(200).body(this.productServiceImpl.getAll(pageNo, pageSize));
    }

    @GetMapping("/getAllSorted")
    ResponseEntity<List<ProductDto>> getAllSorted() {
        return ResponseEntity.status(200).body(this.productServiceImpl.getAllSorted());
    }

    @GetMapping("/addProduct")
    ResponseEntity add(@RequestBody ProductRequest productRequest) {
        this.productServiceImpl.add(productRequest);
        return ResponseEntity.status(200).body("Ürün Eklendi.");
    }

    @GetMapping("/deleteProduct")
    ResponseEntity deleteProduct(@RequestParam Integer id) {
        this.productServiceImpl.deleteProduct(id);
        return ResponseEntity.status(200).body("Ürün silindi.");

    }

    @GetMapping("/activateProduct")
    ResponseEntity activateProduct(@RequestParam Integer id) {
        this.productServiceImpl.activateProduct(id);
        return ResponseEntity.status(200).body(new SuccessResult("Ürün aktif."));
    }

    @GetMapping("/disableProduct")
    ResponseEntity disableProduct(@RequestParam Integer id) {
        this.productServiceImpl.disableProduct(id);
        return ResponseEntity.status(200).body(new SuccessResult("Ürün devre dışı."));
    }

    @GetMapping("/getByProductName")
    ResponseEntity<ProductDto> getByProductNameAndEntityStatus(@RequestParam String productName, @RequestParam EntityStatus entityStatus) {
        return ResponseEntity.status(200).body(this.productServiceImpl.getByProductNameAndEntityStatus(productName, entityStatus));
    }
}
