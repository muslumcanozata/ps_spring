package net.productsellint.api.controllers;

import net.productsellint.business.abstracts.AmountTypeService;
import net.productsellint.core.utilities.results.DataResult;
import net.productsellint.core.utilities.results.Result;
import net.productsellint.dataTransferObjects.concretes.AmountTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<DataResult<List<AmountTypeDto>>> getAll() {
        return this.amountTypeService.getAll();
    }

    @GetMapping("/addType")
    ResponseEntity<Result> add(@RequestParam AmountTypeDto amountTypeDto) {
        return this.amountTypeService.add(amountTypeDto);
    }

    @GetMapping("/deleteAmountType")
    ResponseEntity<Result> deleteAmountType(@RequestParam Integer id) {
        return this.amountTypeService.deleteAmountType(id);
    }

    @GetMapping("/disableAmountType")
    ResponseEntity<Result> disableAmountType(@RequestParam Integer id) {
        return this.amountTypeService.disableAmountType(id);
    }

    @GetMapping("/activateAmountType")
    ResponseEntity<Result> activateAmountType(@RequestParam Integer id) {
        return this.amountTypeService.activateAmountType(id);
    }



    @GetMapping("/getByType")
    ResponseEntity<DataResult<AmountTypeDto>> getByType(@RequestParam String type) {
        return this.amountTypeService.getByType(type);
    }

    @GetMapping("/getById")
    ResponseEntity<DataResult<AmountTypeDto>> getById(@RequestParam Integer id) {
        return this.amountTypeService.getById(id);
    }
}
