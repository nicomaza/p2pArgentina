package com.nicolas.dolar.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "userp2p")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "firstname")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "birthday")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Column(name = "verified")
    private Boolean verified;

    @Column(name = "mail")
    private String mail;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "register_day")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate registerDay;

    @Column(name = "lastlog")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastlog;

    //conexion para ubicar el editor y el cliente

    @OneToMany(mappedBy = "editorEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderEntity> editorOrders;

    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderEntity> clientOrders;

}
