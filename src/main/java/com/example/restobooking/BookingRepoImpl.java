package com.example.restobooking;

import java.util.ArrayList;
import java.util.List;
public class BookingRepoImpl implements BookingRepo {
        private static List<BookingEntity> bookings = new ArrayList<>();
        @Override
        public void save(BookingEntity booking) {
            bookings.add(booking);
            System.out.println("Booking created : " + booking.toString());
        }

        @Override
        public List<BookingEntity> findByDate(String date) {
            List<BookingEntity> bookingsForDay = new ArrayList<>();
            System.out.println("Bookings list  : " + bookings.toString());
            for (BookingEntity booking : bookings) {
                if (booking.getDate().equals(date)) {
                    bookingsForDay.add(booking);
                }
            }
            return bookingsForDay;
        }
}

