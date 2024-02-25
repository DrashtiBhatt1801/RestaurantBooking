package com.example.restobooking;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BookingService {

    private BookingRepo bookingRepository = new BookingRepoImpl();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        public void saveBooking(BookingEntity booking) {
            bookingRepository.save(booking);
        }
        public List<BookingEntity> getBookingsForDay(String date) {
            return bookingRepository.findByDate(date);
        }

        //To Check if booking already exists for the given time and date
        public boolean bookingExists(BookingEntity booking)
        {
            boolean bookingAlreadyExist = false;
            String newBookingTimeString = booking.getTimeSlot();

            LocalTime newBookingTime = LocalTime.parse(newBookingTimeString, formatter);
             bookingAlreadyExist = this.getBookingsForDay(booking.getDate()).stream().anyMatch(x ->
            {
                LocalTime existingBookingTime = LocalTime.parse(x.getTimeSlot(), formatter);
                return ( ((newBookingTime.getHour() == existingBookingTime.getHour()) && (newBookingTime.getMinute() == existingBookingTime.getMinute()))
                        ||((newBookingTime.minusHours(1).getHour() == existingBookingTime.getHour()) && (newBookingTime.getMinute() == existingBookingTime.getMinute()))
                        ||((newBookingTime.plusHours(1).getHour() == existingBookingTime.getHour()) && (newBookingTime.getMinute() == existingBookingTime.getMinute())));
            });
            return bookingAlreadyExist;
        }

        /*Below method is to find next available booking time
        Assumptions : each booking timeslot is of 2:00 hours
        there can be only one booking at a given date and given time
        if there already exist a booking within 2 hours of the time customer is trying to book,
        booking is unsuccessful and  need to show next available time for that dat to customer
         */
        public String findNextAvailBookingTimeForDate(String date)
        {
           List<LocalTime> allBookingTimeFortheDay = this.getBookingsForDay(date).stream().map(x ->
            {
                LocalTime existingBookingTime = LocalTime.parse(x.getTimeSlot(), formatter);
                return existingBookingTime;
            }).collect(Collectors.toList());
            LocalTime latestTime = Collections.max(allBookingTimeFortheDay, LocalTime::compareTo);
            return latestTime.plusHours(2).toString();
        }
}
