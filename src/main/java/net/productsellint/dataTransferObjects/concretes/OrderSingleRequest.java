package net.productsellint.dataTransferObjects.concretes;

import lombok.Data;

@Data
public class OrderSingleRequest {
    private Integer productId;
    private String productName;
    private Float productUnitPrice;
    private Float amount;
    private Integer amountTypeId;
    private String amountTypeType;
    private Float totalPrice;
}


