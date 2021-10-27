package net.productsellint.dataTransferObjects.concretes;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.productsellint.entities.concretes.ProductEntity;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ProductDto {
    @NotNull(message = "id boş bırakılamaz")
    private Integer id;
    @NotNull(message = "isim boş bırakılamaz")
    private String productName;
    @NotNull(message = "ücret boş bırakılamaz")
    private Double unitPrice;
    @NotNull(message = "kategori id boş bırakılamaz")
    private Integer categoryId;
    @NotNull(message = "kategori ismi boş bırakılamaz")
    private String categoryName;
    @NotNull(message = "type id boş bırakılamaz")
    private Integer amountTypeId;
    @NotNull(message = "type ismi boş bırakılamaz")
    private String amountTypeName;
    @NotNull(message = "status boş bırakılamaz")
    private Enum status;
}
