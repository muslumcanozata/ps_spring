package net.productsellint.api.controllers;

import net.productsellint.business.abstracts.AmountTypeService;
import net.productsellint.dataTransferObjects.concretes.AmountTypeDto;
import net.productsellint.dataTransferObjects.concretes.AmountTypeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/amountTypes")
public class AmountTypesController {
    private final AmountTypeService amountTypeService;

    public AmountTypesController(AmountTypeService amountTypeService) {
        this.amountTypeService = amountTypeService;
    }

    @GetMapping("/getAll")
    ResponseEntity<List<AmountTypeDto>> getAll() {
        return ResponseEntity.status(200).body(this.amountTypeService.getAll());
    }

    @GetMapping("/addType")
    ResponseEntity add(@RequestBody AmountTypeRequest amountTypeRequest) {
        this.amountTypeService.add(amountTypeRequest);
        return ResponseEntity.status(200).body("Ürün eklendi.");

    }

    @GetMapping("/deleteAmountType")
    ResponseEntity deleteAmountType(@RequestParam Integer id) {
        this.amountTypeService.deleteAmountType(id);
        return ResponseEntity.status(200).body("Ürün silindi.");

    }

    @GetMapping("/disableAmountType")
    ResponseEntity disableAmountType(@RequestParam Integer id) {
        this.amountTypeService.disableAmountType(id);
        return ResponseEntity.status(200).body("Ürün devre dışı.");

    }

    @GetMapping("/activateAmountType")
    ResponseEntity activateAmountType(@RequestParam Integer id) {
        this.amountTypeService.activateAmountType(id);
        return ResponseEntity.status(200).body("Ürün aktif.");

    }

    @GetMapping("/getByType")
    ResponseEntity<AmountTypeDto> getByType(@RequestParam String type) {
        return ResponseEntity.status(200).body(this.amountTypeService.getByType(type));
    }

    @GetMapping("/getById")
    ResponseEntity<AmountTypeDto> getById(@RequestParam Integer id) {
        return ResponseEntity.status(200).body(this.amountTypeService.getById(id));
    }
}
