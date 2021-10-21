package net.productsellint.dataTransferObjects.concretes;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.productsellint.entities.concretes.ProductEntity;

@Data
@NoArgsConstructor
public class ProductDto {
    private Integer id;
    private String productName;
    private Double unitPrice;
    private Integer categoryId;
    private String categoryName;
    private Integer amountTypeId;
    private String amountTypeName;

    public ProductDto(ProductEntity productEntity) {
        this.id = productEntity.getId();
        this.productName = productEntity.getProductName();
        this.unitPrice = productEntity.getUnitPrice();
        this.categoryId = productEntity.getCategoryEntity().getId();
        this.categoryName = productEntity.getCategoryEntity().getCategoryName();
        this.amountTypeId = productEntity.getAmountTypeEntity().getId();
        this.amountTypeName = productEntity.getAmountTypeEntity().getType();
    }
}
