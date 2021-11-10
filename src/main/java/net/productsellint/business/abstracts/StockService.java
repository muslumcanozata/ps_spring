package net.productsellint.business.abstracts;

import net.productsellint.dataTransferObjects.concretes.StockDto;
import net.productsellint.dataTransferObjects.concretes.StockRequest;

import java.util.List;

public interface StockService {
    List<StockDto> getAll();
    List<StockDto> getAll(int pageNo, int pageSize);
    List<StockDto> getAllSorted();

    void add(StockRequest stockRequest);

    void decreaseStock(Integer id, Float amount);
    void increaseStock(Integer id, Float amount);

    void deleteStock(Integer id);
    void activateStock(Integer id);
    void disableStock(Integer id);
}
