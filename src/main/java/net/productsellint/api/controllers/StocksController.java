package net.productsellint.api.controllers;

import net.productsellint.business.abstracts.StockService;
import net.productsellint.core.utilities.results.Result;
import net.productsellint.core.utilities.results.SuccessResult;
import net.productsellint.dataTransferObjects.concretes.StockDto;
import net.productsellint.dataTransferObjects.concretes.StockRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/stocks")
public class StocksController {
    private final StockService stockService;

    public StocksController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        return ResponseEntity.status(200).body(this.stockService.getAll());
    }

    @GetMapping("/getAllByPage")
    ResponseEntity<List<StockDto>> getAll(@RequestParam int pageNo, @RequestParam int pageSize) {
        return ResponseEntity.status(200).body(this.stockService.getAll(pageNo, pageSize));
    }

    @GetMapping("/getAllSorted")
    ResponseEntity<List<StockDto>> getAllSorted() {
        return ResponseEntity.status(200).body(this.stockService.getAllSorted());
    }

    @GetMapping("/addStock")
    ResponseEntity add(@RequestBody StockRequest stockRequest) {
        this.stockService.add(stockRequest);
        return ResponseEntity.status(200).body("Stock eklendi.");
    }

    @GetMapping("/deleteStock")
    ResponseEntity deleteStock(@RequestParam Integer id) {
        this.stockService.deleteStock(id);
        return ResponseEntity.status(200).body("Stock silindi.");

    }

    @GetMapping("/activateStock")
    ResponseEntity activateStock(@RequestParam Integer id) {
        this.stockService.deleteStock(id);
        return ResponseEntity.status(200).body(new SuccessResult("Stock aktif."));
    }

    @GetMapping("/disableStock")
    ResponseEntity<Result> disableStock(@RequestParam Integer id) {
        this.stockService.disableStock(id);
        return ResponseEntity.status(200).body(new SuccessResult("Stock devre dışı."));
    }
}
