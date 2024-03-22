package com.nicolas.dolar.controller;

import com.nicolas.dolar.dtos.common.ErrorApi;
import com.nicolas.dolar.dtos.detailOrder.UpdateAmountRateRequest;
import com.nicolas.dolar.dtos.order.ResponseOrderDTO;
import com.nicolas.dolar.services.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class OrderDetailController {
    @Autowired
    DetailService detailService;

    @PutMapping("/updateAmount")
    public ResponseEntity<Object> updateAmount(@RequestBody UpdateAmountRateRequest request) {
        try {
            ResponseOrderDTO responseOrderDTO = detailService.updateAmount(request);
            return ResponseEntity.ok(responseOrderDTO);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorApi(LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error al crear la orden.", ex.getMessage()));
        }
    }

    @PutMapping("/updateRate")
    public ResponseEntity<Object> updateRate(@RequestBody UpdateAmountRateRequest request) {
        try {
            ResponseOrderDTO responseOrderDTO = detailService.updateRate(request);
            return ResponseEntity.ok(responseOrderDTO);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorApi(LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error al crear la orden.", ex.getMessage()));
        }
    }
}
