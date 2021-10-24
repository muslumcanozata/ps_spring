package net.productsellint.api.controllers;

import net.productsellint.business.abstracts.CategoryService;
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
    private final CategoryService categoryService;

    @Autowired
    public CategoriesController(CategoryService categoryService) {
        super();
        this.categoryService = categoryService;
    }

    @GetMapping("/getAll")
    ResponseEntity<DataResult<List<CategoryDto>>> getAll() {
        return this.categoryService.getAll();
    }

    @GetMapping("/addCategory")
    ResponseEntity<Result> add(@RequestParam CategoryDto categoryDto) {
        return this.categoryService.add(categoryDto);
    }

    @GetMapping("/deleteCategory")
    ResponseEntity<Result> deleteCategory(@RequestParam Integer id) {
        return this.categoryService.deleteCategory(id);
    }

    @GetMapping("/activateCategory")
    ResponseEntity<Result> activateCategory(@RequestParam Integer id) {
        return this.categoryService.activateCategory(id);
    }

    @GetMapping("/disableCategory")
    ResponseEntity<Result> disableCategory(@RequestParam Integer id) {
        return this.categoryService.disableCategory(id);
    }

    @GetMapping("/getByCategoryName")
    ResponseEntity<DataResult<CategoryDto>> getByCategoryName(@RequestParam String categoryName) {
        return this.categoryService.getByCategoryName(categoryName);
    }

    @GetMapping("/getByCategoryId")
    ResponseEntity<DataResult<CategoryDto>> getById(@RequestParam Integer id) {
        return this.categoryService.getById(id);
    }
}
