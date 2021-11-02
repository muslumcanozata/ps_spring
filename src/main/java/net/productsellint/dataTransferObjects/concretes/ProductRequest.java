package net.productsellint.dataTransferObjects.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String productName;
    private Double unitPrice;
    private Integer categoryId;
    private Integer amountTypeId;
    private Float stock;
    private Integer status;
}
