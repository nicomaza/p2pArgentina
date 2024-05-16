package com.nicolas.dolar.controller;

import com.nicolas.dolar.dtos.common.ErrorApi;
import com.nicolas.dolar.dtos.detailOrder.UpdateAmountRateRequest;
import com.nicolas.dolar.dtos.order.*;
import com.nicolas.dolar.entities.OrderEntity;
import com.nicolas.dolar.entities.UserEntity;
import com.nicolas.dolar.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/newOrder")
    public ResponseEntity<Object> publishNew(@RequestBody @Valid FirstPublishDTO firstPublishDTO) {
        try {
            ResponseOrderDTO responseOrderDTO = orderService.newOrder(firstPublishDTO);
            return ResponseEntity.ok(responseOrderDTO);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorApi(LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error al crear la orden.", ex.getMessage()));
        }
    }

    @PostMapping("/newReview")
    public ResponseEntity<Object> newReview(@RequestBody @Valid ReviewDTO reviewDTO) {
        try {
            OrderEntity orderEntity = orderService.addReview(reviewDTO);
            return ResponseEntity.ok(orderEntity);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorApi(LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error al cargar review.", ex.getMessage()));
        }
    }

    @GetMapping("/getOrderByID/{id}")
    public ResponseEntity<Object> getOrderByID(@PathVariable Long id) {
        try {
            ResponseOrderDTO responseOrderDTO = orderService.getOrderByID(id);
            return ResponseEntity.ok(responseOrderDTO);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorApi(LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error obtener orden", ex.getMessage()));
        }
    }
    @GetMapping("/getOrdersForPublish")
    public ResponseEntity<Object> getOrderForPublish() {
        try {
            List<OrderForPublishDTO> ordersForPublish = orderService.listOrdersForPublish();
            return ResponseEntity.ok(ordersForPublish);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorApi(LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error obtener orden a publicar", ex.getMessage()));
        }
    }

    @GetMapping("/getOrderActiveByUser/{id}")
    public ResponseEntity<Object> getOrderActiveByUser(@PathVariable Long id) {
        try {
            List<ordersActiveByUserDTO> ordersForPublish = orderService.ordersActiveByUser(id);
            return ResponseEntity.ok(ordersForPublish);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorApi(LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error obtener orden a publicar", ex.getMessage()));
        }
    }
    @GetMapping("/getAllOrders")
    public ResponseEntity<Object> getAllOrders() {
        try {
            List<ResponseOrderDTO> responseOrderDTO = orderService.getAllOrders();
            return ResponseEntity.ok(responseOrderDTO);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorApi(LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error obtener orden", ex.getMessage()));
        }
    }

    @GetMapping("/getOrdersToBuyOrSell/")
    public ResponseEntity<Object> getOrdersToBuyOrSell(@RequestParam String type) {
        try {
            List<ResponseOrderDTO> responseOrderDTO = orderService.getAllOrdersByType(type);
            return ResponseEntity.ok(responseOrderDTO);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorApi(LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error obtener orden", ex.getMessage()));
        }
    }

    @PutMapping("/updateTerms")
    public ResponseEntity<Object> updateTerms(@RequestBody UpdateOrderRequestDTO request) {
        try {
            ResponseOrderDTO responseOrderDTO = orderService.updateTerms(request);
            return ResponseEntity.ok(responseOrderDTO);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorApi(LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error al actualizar terminos.", ex.getMessage()));
        }
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<Object> updateStatus(@RequestBody UpdateOrderRequestDTO request) {
        try {
            ResponseOrderDTO responseOrderDTO = orderService.updateStatus(request);
            return ResponseEntity.ok(responseOrderDTO);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorApi(LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error al actualizar estado.", ex.getMessage()));
        }
    }

    @PutMapping("/updateCloseOrder/{id}")
    public ResponseEntity<Object> updateCloseOrder(@PathVariable Long id) {
        try {
            ResponseOrderDTO responseOrderDTO = orderService.updateCloseOrder(id);
            return ResponseEntity.ok(responseOrderDTO);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorApi(LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error al cerrar orden.", ex.getMessage()));
        }
    }
}
