package net.productsellint.dataTransferObjects.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.productsellint.entities.concretes.EntityStatus;
import net.productsellint.entities.concretes.UserEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddressDto {
    @NotNull(message = "id boş bırakılamaz")
    private Integer id;

    @NotNull(message = "kullanıcı id boş bırakılamaz")
    private Integer userId;

    @NotNull(message = "adres ismi boş bırakılamaz")
    private String addressName;

    @NotNull(message = "isim boş bırakılamaz")
    private String name;

    @NotNull(message = "soyisim boş bırakılamaz")
    private String surname;

    @NotNull(message = "il boş bırakılamaz")
    private String province;

    @NotNull(message = "ilçe boş bırakılamaz")
    private String district;

    @NotNull(message = "adres boş bırakılamaz")
    private String address;

    @NotNull(message = "status boş bırakılamaz")
    private Enum status;
}
