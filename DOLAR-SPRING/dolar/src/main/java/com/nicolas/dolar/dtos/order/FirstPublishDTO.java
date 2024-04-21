package com.nicolas.dolar.dtos.order;

import com.nicolas.dolar.dtos.enums.Currency;
import com.nicolas.dolar.dtos.enums.PaymentMethod;
import com.nicolas.dolar.dtos.enums.typePublish;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FirstPublishDTO {

    @NotNull
    @Min(0)
    private Long editorId;

    @Size(max = 100)
    private String terms;

    @NotNull
    private typePublish typePublish;

    @NotNull
    @Min(0)
    private Long amount;

    @NotNull
    private Currency currencyorigin;

    @NotNull
    private Currency currencyend;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    @Min(0)
    private BigDecimal rate;





}
