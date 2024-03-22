package com.nicolas.dolar.services;

import com.nicolas.dolar.dtos.order.FirstPublishDTO;
import com.nicolas.dolar.dtos.order.ResponseOrderDTO;
import com.nicolas.dolar.dtos.order.ReviewDTO;
import com.nicolas.dolar.dtos.order.UpdateOrderRequestDTO;
import com.nicolas.dolar.entities.OrderEntity;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    List<ResponseOrderDTO> getAllOrders();

    ResponseOrderDTO getOrderByID(Long id);

    ResponseOrderDTO newOrder(@Valid FirstPublishDTO firstPublishDTO);

    ResponseOrderDTO setClient(@Valid FirstPublishDTO firstPublishDTO);

    OrderEntity addReview(ReviewDTO review);

    ResponseOrderDTO updateTerms(UpdateOrderRequestDTO updateOrderRequestDTO);
    ResponseOrderDTO updateStatus(UpdateOrderRequestDTO updateOrderRequestDTO);
    ResponseOrderDTO updateCloseOrder(Long id);


}
