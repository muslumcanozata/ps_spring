package net.productsellint.dataTransferObjects.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.productsellint.entities.concretes.EntityStatus;
import net.productsellint.entities.concretes.Gender;
import net.productsellint.entities.concretes.UserAddressEntity;
import net.productsellint.entities.concretes.UserCardEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull(message = "id boş bırakılamaz")
    private Integer id;

    @NotNull(message = "username boş bırakılamaz")
    private String username;

    @NotNull(message = "password boş bırakılamaz")
    private String password;

    @NotNull(message = "ad boş bırakılamaz")
    private String name;

    @NotNull(message = "soyadı boş bırakılamaz")
    private String surname;

    @NotNull(message = "telefon numarası boş bırakılamaz")
    private String phoneNumber;

    @Email(message = "E-Mail should be valid")
    @NotNull(message = "email boş bırakılamaz")
    private String email;

    @NotNull(message = "doğum tarihi boş bırakılamaz")
    private Date birthday;

    @NotNull(message = "cinsiyet boş bırakılamaz")
    private Enum gender;

    @NotNull(message = "password boş bırakılamaz")
    private Boolean isSuperuser;

    @NotNull(message = "kullanıcı kart id'si boş bırakılamaz")
    private Integer userCardId;

    @NotNull(message = "kullanıcı adres id'si boş bırakılamaz")
    private Integer userAddressId;

    @NotNull(message = "status boş bırakılamaz")
    private Enum entityStatus;
}
