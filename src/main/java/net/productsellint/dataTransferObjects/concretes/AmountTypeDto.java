package net.productsellint.dataTransferObjects.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.productsellint.dataAccess.abstracts.AmountTypeDao;
import net.productsellint.entities.concretes.AmountTypeEntity;
import net.productsellint.entities.concretes.EntityStatus;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmountTypeDto {
    @NotNull(message = "id boş bırakılamaz")
    private Integer id;
    @NotNull(message = "type boş bırakılamaz")
    private String type;
    @NotNull(message = "status boş bırakılamaz")
    private Enum status;
}
