package net.productsellint.dataTransferObjects.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTransactionDto {
    @NotNull(message = "id boş bırakılamaz")
    private Integer id;
    @NotNull(message = "ürün id boş bırakılamaz")
    private Integer productId;
    @NotNull(message = "ürün ismi boş bırakılamaz")
    private String productName;
    @NotNull(message = "miktar boş bırakılamaz")
    private Float amount;
    @NotNull(message = "iotype boş bırakılamaz")
    private Boolean iotype;
    @NotNull(message = "tarih boş bırakılamaz")
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date date;
    @NotNull(message = "status boş bırakılamaz")
    private Enum status;
}
