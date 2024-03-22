package com.nicolas.dolar.dtos.order;

import com.nicolas.dolar.dtos.enums.owner;
import com.nicolas.dolar.dtos.enums.typeReview;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewDTO {

    @NotNull
    private Long idOrder;

    private String comment;

    private typeReview typeReview;

    private owner owner;

}
