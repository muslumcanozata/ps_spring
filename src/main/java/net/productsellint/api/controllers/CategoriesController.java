package net.productsellint.api.controllers;

import net.productsellint.business.abstracts.CategoryService;
import net.productsellint.business.concretes.CategoryServiceImpl;
import net.productsellint.core.utilities.results.DataResult;
import net.productsellint.core.utilities.results.Result;
import net.productsellint.dataTransferObjects.concretes.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoriesController {
    private final CategoryServiceImpl categoryServiceImpl;

    @Autowired
    public CategoriesController(CategoryServiceImpl categoryServiceImpl) {
        super();
        this.categoryServiceImpl = categoryServiceImpl;
    }

    @GetMapping("/getAll")
    ResponseEntity<List<CategoryDto>> getAll() {
        return ResponseEntity.status(200).body(this.categoryServiceImpl.getAll());
    }

    @GetMapping("/addCategory")
    ResponseEntity add(@RequestParam CategoryDto categoryDto) {
        this.categoryServiceImpl.add(categoryDto);
        return ResponseEntity.status(200).body("Ürün eklendi.");
    }

    @GetMapping("/deleteCategory")
    ResponseEntity deleteCategory(@RequestParam Integer id) {
        this.categoryServiceImpl.deleteCategory(id);
        return ResponseEntity.status(200).body("Ürün silindi.");
    }

    @GetMapping("/activateCategory")
    ResponseEntity activateCategory(@RequestParam Integer id) {
        this.categoryServiceImpl.activateCategory(id);
        return ResponseEntity.status(200).body("Ürün aktif.");
    }

    @GetMapping("/disableCategory")
    ResponseEntity disableCategory(@RequestParam Integer id) {
        this.categoryServiceImpl.disableCategory(id);
        return ResponseEntity.status(200).body("Ürün devre dışı.");
    }

    @GetMapping("/getByCategoryName")
    ResponseEntity<CategoryDto> getByCategoryName(@RequestParam String categoryName) {
        return ResponseEntity.status(200).body(this.categoryServiceImpl.getByCategoryName(categoryName));
    }

    @GetMapping("/getByCategoryId")
    ResponseEntity<CategoryDto> getById(@RequestParam Integer id) {
        return ResponseEntity.status(200).body(this.categoryServiceImpl.getById(id));
    }
}
