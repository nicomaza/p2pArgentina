package com.nicolas.dolar.services;

import com.nicolas.dolar.dtos.detailOrder.UpdateAmountRateRequest;
import com.nicolas.dolar.dtos.order.ResponseOrderDTO;
import org.springframework.stereotype.Service;

@Service
public interface DetailService {

    ResponseOrderDTO updateAmount(UpdateAmountRateRequest updateAmountRateRequest);

    ResponseOrderDTO updateRate(UpdateAmountRateRequest updateAmountRateRequest);


}
