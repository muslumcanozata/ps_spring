package net.productsellint.api.controllers;

import net.productsellint.business.concretes.OrderServiceImpl;
import net.productsellint.dataTransferObjects.concretes.OrderDto;
import net.productsellint.dataTransferObjects.concretes.OrderRequest;
import net.productsellint.entities.concretes.DeliveryStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/orders")
public class OrdersController {
    private final OrderServiceImpl orderService;

    public OrdersController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/findByDeliveryStatus")
    public ResponseEntity<List<OrderDto>> findByDeliveryStatus(@RequestParam DeliveryStatus deliveryStatus) {
        List<OrderDto> orderDtoList = this.orderService.findByDeliveryStatus(deliveryStatus);
        return ResponseEntity.status(200).body(orderDtoList);
    }

    @GetMapping("/findByDeliveryStatusAndSort")
    public ResponseEntity<List<OrderDto>> findByDeliveryStatusAndSort(@RequestParam DeliveryStatus deliveryStatus) {
        List<OrderDto> orderDtoList = this.orderService.findByDeliveryStatus(deliveryStatus);
        return ResponseEntity.status(200).body(orderDtoList);
    }

    @GetMapping("/findByDeliveryStatusAndPage")
    public ResponseEntity<List<OrderDto>> findByDeliveryStatusAndPage(@RequestParam DeliveryStatus deliveryStatus, @RequestParam int pageNo, @RequestParam int pageSize) {
        List<OrderDto> orderDtoList = this.orderService.findByDeliveryStatus(deliveryStatus);
        return ResponseEntity.status(200).body(orderDtoList);
    }

    @GetMapping("/getAllAndPage")
    public ResponseEntity<List<OrderDto>> getAll(@RequestParam int pageNo, @RequestParam int pageSize) {
        List<OrderDto> orderDtoList = this.orderService.getAll(pageNo, pageSize);
        return ResponseEntity.status(200).body(orderDtoList);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderDto>> getAll() {
        List<OrderDto> orderDtoList = this.orderService.getAll();
        return ResponseEntity.status(200).body(orderDtoList);
    }

    @GetMapping("/getAllSorted")
    public ResponseEntity<List<OrderDto>> getAllSorted() {
        List<OrderDto> orderDtoList = this.orderService.getAllSorted();
        return ResponseEntity.status(200).body(orderDtoList);
    }

    @GetMapping("/getByUUID")
    public ResponseEntity<OrderDto> getByUUID(@RequestBody String uuid) {
        OrderDto orderDto = this.orderService.getByUUID(uuid);
        return ResponseEntity.status(200).body(orderDto);
    }

    @GetMapping("/setOrderToInProcess")
    public ResponseEntity setOrderToInProcess(@RequestParam Integer id) {
        this.orderService.setOrderToInProcess(id);
        return ResponseEntity.status(200).body("Sipariş Durumu: Hazırlanıyor.");
    }

    @GetMapping("/setOrderToOnTheWay")
    public ResponseEntity setOrderToOnTheWay(@RequestParam Integer id) {
        this.orderService.setOrderToOnTheWay(id);
        return ResponseEntity.status(200).body("Sipariş Durumu: Yolda.");
    }
    @GetMapping("/setOrderToDelivered")
    public ResponseEntity setOrderToDelivered(@RequestParam Integer id) {
        this.orderService.setOrderToDelivered(id);
        return ResponseEntity.status(200).body("Sipariş Durumu: Teslim edildi.");
    }

    @GetMapping("/addOrder")
    public ResponseEntity add(@RequestBody OrderRequest orderRequest) {
        this.orderService.add(orderRequest);
        return ResponseEntity.status(200).body("Ürün eklendi.");
    }

    @GetMapping("/deleteOrder")
    public ResponseEntity delete(@RequestParam Integer id) {
        this.orderService.delete(id);
        return ResponseEntity.status(200).body("Ürün silindi.");
    }

    @GetMapping("/disableOrder")
    public ResponseEntity disable(@RequestParam Integer id) {
        this.orderService.disable(id);
        return ResponseEntity.status(200).body("Ürün devre dışı.");
    }

    @GetMapping("/activateOrder")
    public ResponseEntity activate(@RequestParam Integer id) {
        this.orderService.activate(id);
        return ResponseEntity.status(200).body("Ürün aktif.");
    }
}
