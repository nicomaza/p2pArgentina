package com.nicolas.dolar.dtos.detailOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateAmountRateRequest {
    private Long id;
    private Long amount;
    private BigDecimal rate;

}
