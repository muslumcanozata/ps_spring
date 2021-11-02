package net.productsellint.dataTransferObjects.concretes;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Integer id;
    private UUID uuid;
    @NotNull(message = "ürün listesi boş bırakılamaz")
    private List<OrderSingleRequest> orderList;
    @NotNull(message = "Varış zamanı boş bırakılamaz")
    @Temporal(TemporalType.DATE)
    private Date deliveryForecast;
    @NotNull(message = "user id boş bırakılamaz")
    private Integer userId;
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date createdDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date updatedDate;
    @NotNull(message = "kargo durumu boş bırakılamaz")
    private Integer deliveryStatus;
    @NotNull(message = "status boş bırakılamaz")
    private Integer status;
}
