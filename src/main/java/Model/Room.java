package Model;

public record Room(int roomNumber, int amountOfGuests, RoomType roomType, double price) {
}

//    Hotel Reservation System:

//        I want a system for managing hotel room reservations.
//        Each room should have a room number, an amount of guests it can host, a room type, and a price.
//        The room can be a standard room, family, superior or suite.
//        Guests should have a name, an address, and an email.
//        When guests make a reservation, the system saves it with a reservation ID, a check-in date and a check-out date.
//
//        - Create a function that retrieves all available rooms of a certain type during a specific date range for a specific amount of guests.
//        - Get the guest with the most reservations
//        - Write a function which can evaluate if a booking is possible even if
//        the amount of persons in the requested booking exceeds the room size of the biggest room