package net.productsellint.dataTransferObjects.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmountTypeRequest {
    @NotNull(message = "type boş bırakılamaz")
    private String type;
    @NotNull(message = "status boş bırakılamaz")
    private Integer status;
}
