package net.productsellint.dataTransferObjects.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmountTypeDto {
    private Integer id;
    private String type;
    private Integer status;
}
