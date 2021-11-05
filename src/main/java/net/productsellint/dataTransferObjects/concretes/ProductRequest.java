package net.productsellint.dataTransferObjects.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.productsellint.entities.concretes.StockEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String productName;
    private Double unitPrice;
    private Integer categoryId;
    private Integer amountTypeId;
    private StockEntity stock;
    private Integer status;
}
