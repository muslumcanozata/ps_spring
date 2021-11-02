package net.productsellint.api.controllers;

import net.productsellint.business.abstracts.CategoryService;
import net.productsellint.dataTransferObjects.concretes.CategoryDto;
import net.productsellint.entities.concretes.CategoryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoriesController {
    private final CategoryService categoryService;

    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getAll")
    ResponseEntity<List<CategoryDto>> getAll() {
        return ResponseEntity.status(200).body(this.categoryService.getAll());
    }

    @GetMapping("/addCategory")
    ResponseEntity add(@RequestBody CategoryRequest categoryRequest) {
        this.categoryService.add(categoryRequest);
        return ResponseEntity.status(200).body("Ürün eklendi.");
    }

    @GetMapping("/deleteCategory")
    ResponseEntity deleteCategory(@RequestParam Integer id) {
        this.categoryService.deleteCategory(id);
        return ResponseEntity.status(200).body("Ürün silindi.");
    }

    @GetMapping("/activateCategory")
    ResponseEntity activateCategory(@RequestParam Integer id) {
        this.categoryService.activateCategory(id);
        return ResponseEntity.status(200).body("Ürün aktif.");
    }

    @GetMapping("/disableCategory")
    ResponseEntity disableCategory(@RequestParam Integer id) {
        this.categoryService.disableCategory(id);
        return ResponseEntity.status(200).body("Ürün devre dışı.");
    }

    @GetMapping("/getByCategoryName")
    ResponseEntity<CategoryDto> getByCategoryName(@RequestParam String categoryName) {
        return ResponseEntity.status(200).body(this.categoryService.getByCategoryName(categoryName));
    }

    @GetMapping("/getByCategoryId")
    ResponseEntity<CategoryDto> getById(@RequestParam Integer id) {
        return ResponseEntity.status(200).body(this.categoryService.getById(id));
    }
}
