package com.nicolas.dolar.services.Impl;

import com.nicolas.dolar.dtos.enums.owner;
import com.nicolas.dolar.dtos.enums.StatusOrder;
import com.nicolas.dolar.dtos.enums.typeReview;
import com.nicolas.dolar.dtos.order.*;
import com.nicolas.dolar.entities.OrderDetailEntity;
import com.nicolas.dolar.entities.OrderEntity;
import com.nicolas.dolar.entities.UserEntity;
import com.nicolas.dolar.repository.DetailOrderJpaRepository;
import com.nicolas.dolar.repository.OrderJpaRepository;
import com.nicolas.dolar.repository.userJpaRepository;
import com.nicolas.dolar.services.OrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.query.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderJpaRepository orderJpaRepository;

    @Autowired
    userJpaRepository userJpaRepository;

    @Autowired
    DetailOrderJpaRepository detailOrderJpaRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<ResponseOrderDTO> getAllOrders() {
        List<OrderEntity> orders = orderJpaRepository.findAll();
        List<ResponseOrderDTO> responseOrderDTOs = orders.stream()
                .map(order -> modelMapper.map(order, ResponseOrderDTO.class))
                .collect(Collectors.toList());
        return responseOrderDTOs;
    }

    @Override
    public ResponseOrderDTO getOrderByID(Long id) {
        OrderEntity order = orderJpaRepository.findOrderWithDetailsById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró ninguna orden con el ID proporcionado"));


        // Mapear OrderEntity a ResponseOrderDTO
        ResponseOrderDTO responseOrderDTO = modelMapper.map(order, ResponseOrderDTO.class);

        // Mapear la lista de OrderDetailEntity a una lista de ResponseOrderDetailDTO
        List<ResponseOrderDetailDTO> orderDetailDTOList = order.getOrdersDetails().stream()
                .map(orderDetail -> modelMapper.map(orderDetail, ResponseOrderDetailDTO.class))
                .collect(Collectors.toList());


        // Establecer la lista de detalles mapeados en el DTO de respuesta
        responseOrderDTO.setOrderDetails(orderDetailDTOList);
        responseOrderDTO.setIdEditor(order.getEditorEntity().getIdUser());
        return responseOrderDTO;
    }


    @Override
    @Transactional
    public ResponseOrderDTO newOrder(FirstPublishDTO firstPublishDTO) {
        try {
            // Crear una nueva orden
            OrderEntity order = new OrderEntity();
            order.setStatus(StatusOrder.NEW);
            order.setTerms(firstPublishDTO.getTerms());
            order.setDateInit(LocalDateTime.now());

            // Obtener el editor de la orden
            UserEntity editor = userJpaRepository.findById(firstPublishDTO.getEditorId())
                    .orElseThrow(() -> new EntityNotFoundException("No se encontró ningún usuario con el ID proporcionado"));
            order.setEditorEntity(editor);

            // Guardar la orden principal
            order = orderJpaRepository.save(order);

            // Crear y configurar el detalle de la orden
            OrderDetailEntity detail = new OrderDetailEntity();
            detail.setAmount(firstPublishDTO.getAmount());
            detail.setRate(firstPublishDTO.getRate());
            detail.setCurrencyChange(firstPublishDTO.getCurrencyend());
            detail.setCurrencyOrigin(firstPublishDTO.getCurrencyorigin());
            detail.setOperationType(firstPublishDTO.getTypePublish());
            detail.setPaymentMethod(firstPublishDTO.getPaymentMethod());
            detail = detailOrderJpaRepository.save(detail);
            detail.setOrderEntity(order);
            order.getOrdersDetails().add(detail);
            order = orderJpaRepository.save(order);

            ResponseOrderDTO responseDTO = new ResponseOrderDTO();
            responseDTO.setIdOrderp2p(order.getIdOrderp2p());
            responseDTO.setDateInit(order.getDateInit());
            responseDTO.setStatus(order.getStatus());
            responseDTO.setTerms(order.getTerms());

            // Mapear los detalles de la orden a DTOs de detalles de respuesta
            List<ResponseOrderDetailDTO> responseDetailDTOs = order.getOrdersDetails().stream()
                    .map(newDetail -> modelMapper.map(newDetail, ResponseOrderDetailDTO.class))
                    .collect(Collectors.toList());

            responseDTO.setOrderDetails(responseDetailDTOs);
            return responseDTO;
        } catch (NoSuchElementException ex) {
            throw new EntityNotFoundException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    @Override
    public ResponseOrderDTO setClient(FirstPublishDTO firstPublishDTO) {
        return null;
    }


    @Override
    public OrderEntity addReview(ReviewDTO review) {

        OrderEntity order = orderJpaRepository.getReferenceById(review.getIdOrder());

        if (review.getOwner() == owner.EDITOR) {
            order.setCommForClient(review.getComment());
            order.setTypeForClient(review.getTypeReview());
        } else {
            order.setCommForEditor(review.getComment());
            order.setTypeForEditor(review.getTypeReview());
        }

        return orderJpaRepository.save(order);
    }

    @Override
    public ResponseOrderDTO updateTerms(UpdateOrderRequestDTO updateOrderRequestDTO) {

        OrderEntity order = orderJpaRepository.findOrderWithDetailsById(updateOrderRequestDTO.getIdOrder()).orElse(null);

        if (updateOrderRequestDTO.getTerms() == null) {
            throw new NoSuchElementException("No hay terminos escritos");
        }

        order.setTerms(updateOrderRequestDTO.getTerms());


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
    public ResponseOrderDTO updateStatus(UpdateOrderRequestDTO updateOrderRequestDTO) {
        OrderEntity order = orderJpaRepository.findOrderWithDetailsById(updateOrderRequestDTO.getIdOrder()).orElse(null);

        if (updateOrderRequestDTO.getStatus() == null) {
            throw new NoSuchElementException("No hay estado escritos");
        }

        order.setStatus(updateOrderRequestDTO.getStatus());


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
    public ResponseOrderDTO updateCloseOrder(Long id) {
        OrderEntity order = orderJpaRepository.findOrderWithDetailsById(id).orElse(null);


        order.setDateFinish(LocalDateTime.now());
        order.setStatus(StatusOrder.CLOSED);

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
    public List<OrderForPublishDTO> listOrdersForPublish() {

        List<OrderEntity> list = orderJpaRepository.findByStatus(StatusOrder.NEW);

        // Mapear cada OrderEntity a OrdersPublicsDTO
        List<OrderForPublishDTO> orderForPublishDTOList = list.stream()
                .map(orderEntity -> {
                    OrderForPublishDTO orderPublic = new OrderForPublishDTO();
                    orderPublic.setDateInit(orderEntity.getDateInit());
                    orderPublic.setTerms(orderEntity.getTerms());

                    orderPublic.setAmount(orderEntity.getOrdersDetails().get(0).getAmount());
                    orderPublic.setRate(orderEntity.getOrdersDetails().get(0).getRate());
                    orderPublic.setCurrencyOrigin(orderEntity.getOrdersDetails().get(0).getCurrencyOrigin().toString());
                    orderPublic.setCurrencyChange(orderEntity.getOrdersDetails().get(0).getCurrencyChange().toString());
                    orderPublic.setOperationType(orderEntity.getOrdersDetails().get(0).getOperationType().toString());
                    orderPublic.setPaymentMethod(orderEntity.getOrdersDetails().get(0).getPaymentMethod().toString());

                    // Mapear los detalles del editor si es necesario
                    if (orderEntity.getEditorEntity() != null) {
                        orderPublic.setEditorName(orderEntity.getEditorEntity().getName());
                        orderPublic.setEditorLastname(orderEntity.getEditorEntity().getLastname());
                        orderPublic.setEditorPhoto("fotovacio");
                        orderPublic.setEditorFacebook(orderEntity.getEditorEntity().getFacebook());
                        orderPublic.setEditorLastLog(orderEntity.getEditorEntity().getLastlog());
                        orderPublic.setEditorVerified(orderEntity.getEditorEntity().getVerified());

                        orderPublic.setEditorOrdersCompletes(calculateOrdersCompletes(orderEntity.getEditorEntity().getIdUser(), orderEntity.getStatus()));
                        orderPublic.setEditorRegisterDate(orderEntity.getEditorEntity().getRegisterDay());
                        orderPublic.setEditorPositivesOrders(calculatePositiveOrders(orderEntity.getEditorEntity().getIdUser()));
                        // Configurar otros campos del editor si es necesario
                    }

                    return orderPublic;
                })
                .collect(Collectors.toList());

        return orderForPublishDTOList;
    }

    @Override
    public List<ordersActiveByUserDTO> ordersActiveByUser(Long id) {

        List<OrderEntity> list = orderJpaRepository.ordersActiveByUser(StatusOrder.NEW, StatusOrder.PAUSED, id);

        List<ordersActiveByUserDTO> orderForPublishDTOList = list.stream()
                .map(orderEntity -> {
                    ordersActiveByUserDTO orderPublic = new ordersActiveByUserDTO();
                    orderPublic.setDateInit(orderEntity.getDateInit());
                    orderPublic.setTerms(orderEntity.getTerms());
                    orderPublic.setStatus(orderEntity.getStatus());
                    orderPublic.setIdOrderp2p(orderEntity.getIdOrderp2p());

                    orderPublic.setAmount(orderEntity.getOrdersDetails().get(0).getAmount());
                    orderPublic.setRate(orderEntity.getOrdersDetails().get(0).getRate());
                    orderPublic.setCurrencyOrigin(orderEntity.getOrdersDetails().get(0).getCurrencyOrigin().toString());
                    orderPublic.setCurrencyChange(orderEntity.getOrdersDetails().get(0).getCurrencyChange().toString());
                    orderPublic.setOperationType(orderEntity.getOrdersDetails().get(0).getOperationType().toString());
                    orderPublic.setPaymentMethod(orderEntity.getOrdersDetails().get(0).getPaymentMethod().toString());


                    return orderPublic;
                })
                .collect(Collectors.toList());

        return orderForPublishDTOList;
    }


    private int calculatePositiveOrders(Long idUser) {
        Long count = orderJpaRepository.countNumberOfPositives(idUser, typeReview.POSITIVE);
        return count.intValue();
    }

    private String calculateOrdersCompletes(Long userId, StatusOrder status) {
        return orderJpaRepository.countCompletedOrdersByUserId(userId, StatusOrder.SUCCESS).toString();
    }


}
