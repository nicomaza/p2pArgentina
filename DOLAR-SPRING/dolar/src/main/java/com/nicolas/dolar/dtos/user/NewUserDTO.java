package com.nicolas.dolar.dtos.user;

import com.nicolas.dolar.dtos.enums.owner;
import com.nicolas.dolar.dtos.enums.typeReview;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewUserDTO {
    @NotNull
    private String name;
    @NotNull
    private String lastname;
    @NotNull
    private LocalDate birthday;

    @Email
    private String mail;
    @NotNull
    private Long phone;
    @NotNull
    private String facebook;


}
