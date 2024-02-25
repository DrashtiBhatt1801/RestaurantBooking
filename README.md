This application runs on localhost port : 8080
you can book a table at a restarant using POST request URL : https://localhost:8080/book
To do the booking, JSON input in the RequestBody needs to be provided(below is an example)
{
    "custName": "Drashti",
    "date": "2024-02-12",
    "tableSize":"4",
    "timeSlot":"15:00"
}
you can see all the bookings for a given date using GET Request URL : https://localhost:8080/show-bookings/{date}
date needs to be passed as a Path Parameter for ex : https://localhost:8080/show-bookings/2024-02-12

Assumptions 
Each booking timeslot is of 2:00 hours.
There can be only one booking at a given date and given time.
If there already exists a booking within 2 hours of the time that the customer is trying to book,
new booking will be unsuccessful and it will show next available time for that date to the customer.
