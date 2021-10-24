package net.productsellint.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="amount_types")
public class AmountTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "type", nullable = false, unique = true)
    private String type;

    @OneToMany(mappedBy = "amountTypeEntity")
    private List<ProductEntity> productEntities;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private EntityStatus entityStatus;
}
