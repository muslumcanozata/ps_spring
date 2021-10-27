package net.productsellint.dataTransferObjects.concretes;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.productsellint.entities.concretes.AmountTypeEntity;
import net.productsellint.entities.concretes.CategoryEntity;
import net.productsellint.entities.concretes.EntityStatus;
import net.productsellint.entities.concretes.ProductEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @NotNull(message = "id boş bırakılamaz")
    private Integer id;
    @NotNull(message = "uuid boş bırakılamaz")
    private UUID uuid;
    @NotNull(message = "ürün id boş bırakılamaz")
    private Integer productId;
    @NotNull(message = "ürün ismi boş bırakılamaz")
    private String productName;
    @NotNull(message = "kategori id boş bırakılamaz")
    private Integer categoryId;
    @NotNull(message = "kategori ismi boş bırakılamaz")
    private String categoryName;
    @NotNull(message = "type id boş bırakılamaz")
    private Integer amountTypeId;
    @NotNull(message = "type ismi boş bırakılamaz")
    private String amountTypeName;
    @NotNull(message = "miktar boş bırakılamaz")
    private Float amount;
    @NotNull(message = "tarih boş bırakılamaz")
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date date;
    @NotNull(message = "status boş bırakılamaz")
    private Enum status;
}
