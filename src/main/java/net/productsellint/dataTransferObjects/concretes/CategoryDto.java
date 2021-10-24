package net.productsellint.dataTransferObjects.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    @NotNull(message = "id boş bırakılamaz")
    private Integer id;
    @NotNull(message = "isim boş bırakılamaz")
    private String categoryName;
    @NotNull(message = "status boş bırakılamaz")
    private Integer status;
}
