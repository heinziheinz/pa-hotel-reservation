package Model;

import java.util.List;

public record Reservations (List<Reservation> availableRooms, List<Reservation> notAvailableRooms){
}
