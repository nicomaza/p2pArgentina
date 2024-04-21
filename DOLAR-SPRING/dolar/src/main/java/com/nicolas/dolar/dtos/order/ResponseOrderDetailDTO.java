package com.nicolas.dolar.dtos.order;

import com.nicolas.dolar.dtos.enums.PaymentMethod;
import com.nicolas.dolar.dtos.enums.Currency;
import com.nicolas.dolar.dtos.enums.typePublish;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseOrderDetailDTO {

    private Long idOrderDetail;
    private Long amount;
    private BigDecimal rate;
    private Currency currencyOrigin;
    private Currency currencyChange;
    private typePublish operationType;
    private PaymentMethod paymentMethod;
}
