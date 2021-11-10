package net.productsellint.dataTransferObjects.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    @Email
    private String email;
    private Date birthday;
    private Integer gender;
    private Boolean isSuperuser;
    private Integer entityStatus;
}
