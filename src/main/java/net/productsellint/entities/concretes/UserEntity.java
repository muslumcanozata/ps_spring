package net.productsellint.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Email(message = "E-Mail should be valid")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "is_superuser", nullable = false)
    private Boolean isSuperuser;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    private List<UserCardEntity> userCardEntities;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    private List<UserAddressEntity> userAddressEntities;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private EntityStatus entityStatus;
}