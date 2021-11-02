package net.productsellint.dataTransferObjects.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.productsellint.entities.concretes.DeliveryStatus;
import net.productsellint.entities.concretes.EntityStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private List<OrderSingleRequest> orderSingleRequestList;
    @Temporal(TemporalType.DATE)
    private Date deliveryForecast;
    private Integer userId;
    private DeliveryStatus deliveryStatus;
    private EntityStatus entityStatus;
}
