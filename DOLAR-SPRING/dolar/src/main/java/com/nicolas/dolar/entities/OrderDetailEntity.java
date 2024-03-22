package com.nicolas.dolar.entities;

import com.nicolas.dolar.dtos.enums.currency;
import com.nicolas.dolar.dtos.enums.PaymentMethod;
import com.nicolas.dolar.dtos.enums.typePublish;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "orderdetail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_detail")
    private Long idOrderDetail;

    @Column(name = "operation_type")
    private typePublish operationType;

    @Column(name = "currency_origin")
    private currency currencyOrigin;

    @Column(name = "currency_change")
    private currency currencyChange;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "rate")
    private BigDecimal rate;

    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order")
    private OrderEntity orderEntity;
}
