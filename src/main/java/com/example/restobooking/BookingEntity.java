package com.example.restobooking;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class BookingEntity {

    private String custName;
    private String date;
    private int tableSize;
    private String timeSlot;

}
