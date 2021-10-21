package net.productsellint.api.controllers;

import net.productsellint.business.abstracts.AmountTypeService;
import net.productsellint.core.utilities.results.DataResult;
import net.productsellint.core.utilities.results.Result;
import net.productsellint.dataTransferObjects.concretes.AmountTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/amountTypes")
public class AmountTypesController {
    private final AmountTypeService amountTypeService;

    @Autowired
    public AmountTypesController(AmountTypeService amountTypeService) {
        super();
        this.amountTypeService = amountTypeService;
    }

    @GetMapping("/getAll")
    DataResult<List<AmountTypeDto>> getAll() {
        return this.amountTypeService.getAll();
    }

    @GetMapping("/addType")
    Result add(@RequestParam AmountTypeDto amountTypeDto) {
        return this.amountTypeService.add(amountTypeDto);
    }

    @GetMapping("/dropAmountType")
    Result drop(@RequestParam Integer id) {
        return this.amountTypeService.drop(id);
    }

    @GetMapping("/getByType")
    DataResult<AmountTypeDto> getByType(@RequestParam String type) {
        return this.amountTypeService.getByType(type);
    }

    @GetMapping("/getById")
    DataResult<AmountTypeDto> getById(@RequestParam Integer id) {
        return this.amountTypeService.getById(id);
    }
}
