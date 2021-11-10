package net.productsellint.dataTransferObjects.concretes;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {
    private Integer id;
    private String productName;
    private Double unitPrice;
    private Integer categoryId;
    private String categoryName;
    private Integer amountTypeId;
    private String amountTypeType;
    private Float stock;
    private Enum status;
}
