package com.example.galloween2.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ownerName;

    private Long cardNumber;

    private Long cvv;

    private String expirationDate;

    /*@OneToOne(mappedBy = "payment")
    private Reservation reservation;*/

    @OneToOne(mappedBy = "payment")
    private ReservationStatus reservationStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
