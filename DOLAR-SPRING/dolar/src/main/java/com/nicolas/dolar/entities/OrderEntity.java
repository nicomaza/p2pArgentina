package com.nicolas.dolar.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nicolas.dolar.dtos.enums.StatusOrder;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.nicolas.dolar.dtos.enums.typeReview;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orderp2p")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long idOrderp2p;

    @Column(name = "datetime_init")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateInit;

    @Column(name = "datetime_finish")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateFinish;

    @Column(name = "status")
    private StatusOrder status;


    @Column(name = "terms")
    private String terms;

    //review

    @Column(name = "comm_foreditor")
    private String commForEditor;

    @Column(name = "type_foreditor")
    private typeReview typeForEditor;

    @Column(name = "comm_forclient")
    private String commForClient;

    @Column(name = "type_forclient")
    private typeReview TypeForClient;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_editor", referencedColumnName = "id_user")
    @JsonBackReference

    private UserEntity editorEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client", referencedColumnName = "id_user")
    @JsonBackReference

    private UserEntity clientEntity;

    @OneToMany(mappedBy = "orderEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore // Evita la serialización circular
    private List<OrderDetailEntity> ordersDetails  = new ArrayList<>();


    @Transient
    private List<OrderDetailEntity> ordersDetailsList;


}
