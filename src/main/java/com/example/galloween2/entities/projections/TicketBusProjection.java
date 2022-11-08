package com.example.galloween2.entities.projections;

import com.example.galloween2.entities.Destination;
import com.example.galloween2.entities.Reservation;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public interface TicketBusProjection {

    Long getId();

    String getDeparture_date();

    Long getSeat_number();

    String getClass_type();

    String getOrigin();

    String getCheck_in_time();

    Long getCost();
}
