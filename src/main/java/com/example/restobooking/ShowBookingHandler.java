package com.example.restobooking;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.muserver.MuRequest;
import io.muserver.MuResponse;
import io.muserver.RouteHandler;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ShowBookingHandler implements RouteHandler {

    private BookingService bookingService= new BookingService();

    @Override
    public void handle(MuRequest muRequest, MuResponse muResponse, Map<String, String> map) throws Exception {
        String pathDate = map.get("date");
        System.out.println("Booking requested for date : " + pathDate);
        List<BookingEntity> bookingsForDay = bookingService.getBookingsForDay(pathDate);
        String jsonResult = convertListToJson(bookingsForDay);
        muResponse.status(200);
        muResponse.sendChunk(jsonResult);
    }

    public static String convertListToJson(List<BookingEntity> bookings) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(bookings);
        } catch (IOException e) {
            e.printStackTrace();
            return "[]"; // Return an empty array in case of an error
        }
    }
}
