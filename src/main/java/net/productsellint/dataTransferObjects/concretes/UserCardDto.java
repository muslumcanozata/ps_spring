package net.productsellint.dataTransferObjects.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.productsellint.entities.concretes.EntityStatus;
import net.productsellint.entities.concretes.UserEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCardDto {
    @NotNull(message = "id boş bırakılamaz")
    private Integer id;

    @NotNull(message = "kullanıcı id boş bırakılamaz")
    private Integer userId;

    @NotNull(message = "kart numarası boş bırakılamaz")
    private Long cardNumber;

    @NotNull(message = "skt boş bırakılamaz")
    @DateTimeFormat(pattern = "MM/yy")
    private Date expiryDate;

    @NotNull(message = "cvc boş bırakılamaz")
    private String cvc;

    @NotNull(message = "status boş bırakılamaz")
    private Enum status;
}
