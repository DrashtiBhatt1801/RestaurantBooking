package com.example.restobooking;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.muserver.MuRequest;
import io.muserver.MuResponse;
import io.muserver.RouteHandler;
import java.io.IOException;
import java.util.Map;


public class RequestBookingHandler implements RouteHandler {

    private  BookingService bookingService = new BookingService();

    @Override
    public void handle(MuRequest muRequest, MuResponse muResponse, Map<String, String> map) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            BookingEntity booking = objectMapper.readValue(muRequest.readBodyAsString(), BookingEntity.class);

            //check if booking already exists for the same date and time
            if(bookingService.bookingExists(booking))
            {
                muResponse.sendChunk("Sorry,Booking already exist for : " + booking.getDate()+" at: "+booking.getTimeSlot());
                muResponse.sendChunk(" Next available time for booking on : " + booking.getDate()+" is at: "+ bookingService.findNextAvailBookingTimeForDate(booking.getDate()));
            }
            else {
                bookingService.saveBooking(booking);
                muResponse.status(201);
                muResponse.sendChunk("Booking created for : " + booking.getDate() +" at: "+booking.getTimeSlot());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
