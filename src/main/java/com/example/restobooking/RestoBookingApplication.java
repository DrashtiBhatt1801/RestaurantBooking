package com.example.restobooking;

import io.muserver.*;


public class RestoBookingApplication {
	public static void main(String[] args) {

		MuServer server = MuServerBuilder.httpServer()
				.withHttpsPort(8080)
				.addHandler(Method.GET, "/", (request, response, pathParams) -> {
					response.write("Hello, world");})
				.addHandler(Method.POST, "/book", new RequestBookingHandler())
				.addHandler(Method.GET, "/show-bookings/{date}", new ShowBookingHandler())
				.start();
		System.out.println("Started server at " + server.uri());
	}

}