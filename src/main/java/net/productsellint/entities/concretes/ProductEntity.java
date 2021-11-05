package net.productsellint.entities.concretes;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "product_name", nullable = false, unique = true)
    private String productName;

    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private CategoryEntity categoryEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "amounttype_id", referencedColumnName = "id")
    private AmountTypeEntity amountTypeEntity;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stocks_id", referencedColumnName = "id")
    private StockEntity stockEntity;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private EntityStatus entityStatus;

    public ProductEntity(String productName, Double unitPrice, CategoryEntity categoryEntity, AmountTypeEntity amountTypeEntity, StockEntity stockEntity, EntityStatus entityStatus) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.categoryEntity = categoryEntity;
        this.amountTypeEntity = amountTypeEntity;
        this.stockEntity = stockEntity;
        this.entityStatus = entityStatus;
    }
}
