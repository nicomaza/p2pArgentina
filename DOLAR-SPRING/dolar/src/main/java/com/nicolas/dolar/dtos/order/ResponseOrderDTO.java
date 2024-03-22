package com.nicolas.dolar.dtos.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nicolas.dolar.dtos.enums.StatusOrder;
import com.nicolas.dolar.dtos.enums.typeReview;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseOrderDTO {

    private Long idOrderp2p;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateInit;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateFinish;
    private StatusOrder status;
    private String terms;
    private String commForEditor;
    private typeReview typeForEditor;
    private String commForClient;
    private typeReview typeForClient;
    private List<ResponseOrderDetailDTO> orderDetails;
    private Long idEditor;

}
