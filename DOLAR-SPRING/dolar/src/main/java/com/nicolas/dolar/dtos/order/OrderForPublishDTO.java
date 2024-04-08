package com.nicolas.dolar.dtos.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderForPublishDTO {

    private LocalDateTime dateInit;
    private String terms;
    private Long amount;
    private BigDecimal rate;
    private String currencyOrigin;
    private String currencyChange;
    private String operationType;
    private String paymentMethod;

    private String editorName;
    private String editorLastname;
    private String editorPhoto;
    private String editorFacebook;
    private LocalDateTime editorLastLog;
    private Boolean editorVerified;
    private String editorOrdersCompletes;
    private int editorPositivesOrders;
    private LocalDate editorRegisterDate;




}
