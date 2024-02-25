package com.example.restobooking;

import java.util.List;

public interface BookingRepo {
    void save(BookingEntity booking);

   List<BookingEntity> findByDate(String date);
}
