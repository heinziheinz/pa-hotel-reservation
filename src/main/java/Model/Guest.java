package Model;

public record Guest(int id, String name, String email) {
}


//        Guests should have a name, an address, and an email.
//        When guests make a reservation, the system saves it with a reservation ID, a check-in date and a check-out date.