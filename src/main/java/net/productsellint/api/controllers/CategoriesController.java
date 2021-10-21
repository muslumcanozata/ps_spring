package net.productsellint.api.controllers;

import net.productsellint.business.abstracts.CategoryService;
import net.productsellint.core.utilities.results.DataResult;
import net.productsellint.core.utilities.results.Result;
import net.productsellint.dataTransferObjects.concretes.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
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
    DataResult<List<CategoryDto>> getAll() {
        return this.categoryService.getAll();
    }

    @GetMapping("/addCategory")
    Result add(@RequestParam CategoryDto categoryDto) {
        return this.categoryService.add(categoryDto);
    }

    @GetMapping("/dropCategory")
    Result drop(@RequestParam Integer id) {
        return this.categoryService.drop(id);
    }

    @GetMapping("/getByCategoryName")
    DataResult<CategoryDto> getByCategoryName(@RequestParam String categoryName) {
        return this.categoryService.getByCategoryName(categoryName);
    }

    @GetMapping("/getByCategoryId")
    DataResult<CategoryDto> getById(@RequestParam Integer id) {
        return this.categoryService.getById(id);
    }
}
