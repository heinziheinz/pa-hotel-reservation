package Model;

import java.time.LocalDate;

public record Reservation(int id, LocalDate startDate, LocalDate endDate, Room room, Guest guest, int numberOfGuets){
}
