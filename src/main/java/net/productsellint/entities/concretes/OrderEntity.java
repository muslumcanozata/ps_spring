package net.productsellint.entities.concretes;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;

    @Type(type = "jsonb")
    @Column(name = "order_list")
    private String orderList;

    @Column(name = "delivery_forecast", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date deliveryForecast;

    @Column(name = "created_date", nullable = false)
    @CreationTimestamp
    private Date createdDate;

    @Column(name = "updated_date", nullable = false)
    @UpdateTimestamp
    private Date updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "delivery_status")
    private DeliveryStatus deliveryStatus;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private EntityStatus entityStatus;

    public OrderEntity(String orderList, Date deliveryForecast, Integer userId, DeliveryStatus deliveryStatus, EntityStatus entityStatus) {
        this.orderList = orderList;
        this.deliveryForecast = deliveryForecast;
        this.userEntity.setId(userId);
        this.deliveryStatus = deliveryStatus;
        this.entityStatus = entityStatus;
    }
}
