package com.example.restobooking;

import io.muserver.MuRequest;
import io.muserver.MuResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import static org.mockito.Mockito.*;

class RestoBookingApplicationTests {

	@Test
	public void testRequestBookingHandler() throws Exception {

		String pathDate = "2024-02-20";
		String request = "{\"custName\": \"Drashti\",\"date\": \"2024-02-20\",\"tableSize\":\"4\",\"timeSlot\":\"20:00\"}";
		Map<String, String> pathParams = Collections.singletonMap("date", pathDate);
		MuRequest muRequestMock = mock(MuRequest.class);
		MuResponse muResponseMock = mock(MuResponse.class);
		BookingService bookingServiceMock = mock(BookingService.class);
		RequestBookingHandler requestBookingHandler = new RequestBookingHandler();
		// Mocking getBookingsForDay method
		List<BookingEntity> mockBookings = Collections.singletonList(new BookingEntity());
		when(bookingServiceMock.getBookingsForDay(pathDate)).thenReturn(mockBookings);
		// Mocking readBodyAsString method
		when(muRequestMock.readBodyAsString()).thenReturn(request);

		requestBookingHandler.handle(muRequestMock, muResponseMock, pathParams);
		verify(muResponseMock).status(201);
		verify(muResponseMock).sendChunk("Booking created for : 2024-02-20 at: 20:00");
	}

	@Test
	public void testShowBookingsForDay() throws Exception {
		// Arrange
		BookingService bookingServiceMock = Mockito.mock(BookingService.class);
		ShowBookingHandler showBookingHandler = new ShowBookingHandler();
		String pathDate = "2024-02-20";
		Map<String, String> pathParams = Collections.singletonMap("date", pathDate);
		MuRequest muRequestMock = mock(MuRequest.class);
		MuResponse muResponseMock = mock(MuResponse.class);
		BookingEntity booking = new BookingEntity();
		booking.setCustName("Drashti");
		booking.setDate("2024-03-01");
		booking.setTableSize(4);
		booking.setTimeSlot("20:00");
		List<BookingEntity> mockBookings = new ArrayList<>();
		mockBookings.add(booking);
		when(bookingServiceMock.getBookingsForDay("2024-03-01")).thenReturn(mockBookings);
		showBookingHandler.handle(muRequestMock, muResponseMock, pathParams);
		verify(muResponseMock).status(200);
	}
}
