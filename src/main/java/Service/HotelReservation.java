package Service;

import Model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HotelReservation {
    //        - Create a function that retrieves
    //        all available rooms of a certain type during a specific date range for a specific amount of guests.
    public List<Room> getAllAvailableRooms(RoomType roomType, LocalDate startDate, LocalDate endDate, Reservations reservations, int numberOfGuests) {


        return reservations.availableRooms().stream().filter(reservation -> {
                    return isRoomFree(startDate, reservation);
                }).filter(reservation -> {
                    System.out.println("reservation = " + reservation);
                    return reservation.room().roomType().equals(roomType) && reservation.numberOfGuets() == numberOfGuests;
                })
                .map(reservation -> reservation.room()).toList();
    }

//    private boolean isRoomFree(LocalDate startDate, LocalDate endDate, Reservation reservation) {
//        return (reservation.startDate().isAfter(startDate) || reservation.endDate().isBefore(startDate));
//    }



    public Guest guestWithMostReservations(Reservations reservations) {
        //    This was not tested
        return reservations.notAvailableRooms().stream()
                .map(Reservation::guest)
                .collect(Collectors.groupingBy(
                        guest -> guest,
                        Collectors.counting()
                )).entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);

    }

    public Guest guestWithMostReservationsTwo(List<Reservation> reservations) {
        //    This was not tested
        return reservations.stream()
                .map(Reservation::guest)
                .collect(Collectors.groupingBy(
                        guest -> guest,
                        Collectors.counting()
                )).entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);

    }

    public List<Room> getAllAvailableRoomsTwo(List<Reservation> reservations, List<Room> allRooms, LocalDate startDate, LocalDate endDate) {


        return roomsAvailable(reservations, allRooms, startDate);
    }

    private List<Room> roomsAvailable(List<Reservation> reservations, List<Room> allRooms, LocalDate startDate) {
        List<Room> reservedRooms = reservations.stream().filter(reservation -> {
            System.out.println(isRoomFree( startDate,  reservation));
            return isRoomFree( startDate,  reservation);
        }).map(Reservation::room).toList();
        System.out.println("reservedRooms = " + reservedRooms);
        List<Room> availableRooms = allRooms.stream()
                .filter(room -> {
                    System.out.println("!reservedRooms.contains(room):" + !reservedRooms.contains(room));
                   return !reservedRooms.contains(room);
                })
                .collect(Collectors.toList());

        return availableRooms;
    }

    public boolean possibleDespiteHighestExceeded(List<Reservation> reservations, List<Room> allRooms, LocalDate startDate, LocalDate endDate, int numberOfGuests) {
        List<Room> availableRooms = roomsAvailable(reservations, allRooms, startDate);
        System.out.println("availableRooms = " + availableRooms);

        int capacity = availableRooms.stream().mapToInt(Room::amountOfGuests).sum();
        return capacity >= numberOfGuests;
    }

    private boolean isRoomFree(LocalDate startDate, Reservation reservation) {
        return (reservation.startDate().isAfter(startDate) || reservation.endDate().isBefore(startDate));
    }

    //TODO implement instead of isRoomFree: logic works!! two parameters are needed for that LocalDate startDate,LocalDate endDate
    private boolean isRoomFreeTwo(LocalDate startDate,LocalDate endDate, Reservation reservation) {
        return (reservation.startDate().isAfter(endDate) || reservation.endDate().isBefore(startDate));
    }
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
//        the amount of persons in the requested booking exceeds the room size of the biggest roo
