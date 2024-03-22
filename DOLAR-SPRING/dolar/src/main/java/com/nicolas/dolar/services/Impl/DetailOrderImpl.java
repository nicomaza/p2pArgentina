package com.nicolas.dolar.services.Impl;

import com.nicolas.dolar.dtos.detailOrder.UpdateAmountRateRequest;
import com.nicolas.dolar.dtos.order.ResponseOrderDTO;
import com.nicolas.dolar.entities.OrderEntity;
import com.nicolas.dolar.repository.OrderJpaRepository;
import com.nicolas.dolar.services.DetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailOrderImpl implements DetailService {

    @Autowired
    OrderJpaRepository orderJpaRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public ResponseOrderDTO updateAmount(UpdateAmountRateRequest updateAmountRateRequest) {
        OrderEntity order = orderJpaRepository.findOrderWithDetailsById(updateAmountRateRequest.getId()).orElse(null);

        if (order == null) {

        }

        order.getOrdersDetails().get(0).setAmount(updateAmountRateRequest.getAmount());


        orderJpaRepository.save(order);

        try {

            ResponseOrderDTO response = modelMapper.map(order, ResponseOrderDTO.class);
            return response;
        } catch (Exception e) {

            String errorMessage = "Error al mapear OrderEntity a ResponseOrderDTO: " + e.getMessage();
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public ResponseOrderDTO updateRate(UpdateAmountRateRequest updateAmountRateRequest) {
        OrderEntity order = orderJpaRepository.findOrderWithDetailsById(updateAmountRateRequest.getId()).orElse(null);

        if (order == null) {

        }

        order.getOrdersDetails().get(0).setRate(updateAmountRateRequest.getRate());


        orderJpaRepository.save(order);

        try {

            ResponseOrderDTO response = modelMapper.map(order, ResponseOrderDTO.class);
            return response;
        } catch (Exception e) {

            String errorMessage = "Error al mapear OrderEntity a ResponseOrderDTO: " + e.getMessage();
            throw new RuntimeException(errorMessage);
        }
    }
}
