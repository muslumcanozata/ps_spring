package net.productsellint.dataTransferObjects.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCardDto {
    private Integer id;
    private Integer userId;
    private Long cardNumber;
    @DateTimeFormat(pattern = "MM/yy")
    private Date expiryDate;
    private String cvc;
    private Integer status;
}
