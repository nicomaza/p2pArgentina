package com.nicolas.dolar.dtos.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nicolas.dolar.dtos.enums.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ordersActiveByUserDTO {
    private Long idOrderp2p;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateInit;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateFinish;
    private StatusOrder status;
    private String terms;
    private Long amount;
    private BigDecimal rate;
    private String currencyOrigin;
    private String currencyChange;
    private String operationType;
    private String paymentMethod;
}
