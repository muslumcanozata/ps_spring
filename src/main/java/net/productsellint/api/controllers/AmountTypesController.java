package net.productsellint.api.controllers;

import net.productsellint.business.abstracts.AmountTypeService;
import net.productsellint.business.concretes.AmountTypeServiceImpl;
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
    private final AmountTypeServiceImpl amountTypeServiceImpl;

    @Autowired
    public AmountTypesController(AmountTypeServiceImpl amountTypeServiceImpl) {
        super();
        this.amountTypeServiceImpl = amountTypeServiceImpl;
    }

    @GetMapping("/getAll")
    ResponseEntity<List<AmountTypeDto>> getAll() {
        return ResponseEntity.status(200).body(this.amountTypeServiceImpl.getAll());
    }

    @GetMapping("/addType")
    ResponseEntity add(@RequestParam AmountTypeDto amountTypeDto) {
        this.amountTypeServiceImpl.add(amountTypeDto);
        return ResponseEntity.status(200).body("Ürün eklendi.");

    }

    @GetMapping("/deleteAmountType")
    ResponseEntity deleteAmountType(@RequestParam Integer id) {
        this.amountTypeServiceImpl.deleteAmountType(id);
        return ResponseEntity.status(200).body("Ürün silindi.");

    }

    @GetMapping("/disableAmountType")
    ResponseEntity disableAmountType(@RequestParam Integer id) {
        this.amountTypeServiceImpl.disableAmountType(id);
        return ResponseEntity.status(200).body("Ürün devre dışı.");

    }

    @GetMapping("/activateAmountType")
    ResponseEntity activateAmountType(@RequestParam Integer id) {
        this.amountTypeServiceImpl.activateAmountType(id);
        return ResponseEntity.status(200).body("Ürün aktif.");

    }

    @GetMapping("/getByType")
    ResponseEntity<AmountTypeDto> getByType(@RequestParam String type) {
        return ResponseEntity.status(200).body(this.amountTypeServiceImpl.getByType(type));
    }

    @GetMapping("/getById")
    ResponseEntity<AmountTypeDto> getById(@RequestParam Integer id) {
        return ResponseEntity.status(200).body(this.amountTypeServiceImpl.getById(id));
    }
}
