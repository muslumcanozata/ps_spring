package net.productsellint.dataTransferObjects.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddressDto {
    private Integer id;
    private Integer userId;
    private String addressName;
    private String name;
    private String surname;
    private String province;
    private String district;
    private String address;
    private Integer status;
}
