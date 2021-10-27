package net.productsellint.dataTransferObjects.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {
    @NotNull(message = "id boş bırakılamaz")
    private Integer id;
    @NotNull(message = "ürün id'si boş bırakılamaz")
    private Integer productId;
    @NotNull(message = "ürün ismi boş bırakılamaz")
    private String productName;
    @NotNull(message = "ürün fiyatı boş bırakılamaz")
    private Double productUnitPrice;
    @NotNull(message = "kategori id boş bırakılamaz")
    private Integer categoryId;
    @NotNull(message = "kategori ismi boş bırakılamaz")
    private String categoryName;
    @NotNull(message = "type id boş bırakılamaz")
    private Integer amountTypeId;
    @NotNull(message = "type ismi boş bırakılamaz")
    private String amountTypeName;
    @NotNull(message = "kritik miktar boş bırakılamaz")
    private Float criticalAmount;
    @NotNull(message = "güncel miktar boş bırakılamaz")
    private Float amount;
    @NotNull(message = "status boş bırakılamaz")
    private Enum status;
}
