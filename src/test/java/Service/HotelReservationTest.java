package Service;

import Model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HotelReservationTest {

    @Test
    void getAllAvailableRooms() {

//        RoomType roomType, LocalDate start, java.time.LocalDate end, Reservations reservations
        Room roomOne = new Room(1, 2, RoomType.ROOM, 200.00);
        Room roomTwo = new Room(1, 2, RoomType.ROOM, 200.00);
        Guest guestOne = new Guest(1, "Frank", "gg@gg");
        Guest guestTwo = new Guest(2, "Frank", "gg@gg");
        Reservation reservationOne = new Reservation(1,LocalDate.of(2023,9,21),LocalDate.of(2023,9,22),roomOne, guestOne, 1 );
        Reservation reservationTwo = new Reservation(1,LocalDate.of(2023,9,21),LocalDate.of(2023,9,22),roomTwo, guestTwo, 2 );
        List<Reservation> availableList = List.of( reservationOne);
        List<Reservation> reserverdList = List.of( reservationTwo);

        Reservations reservations = new Reservations(availableList, reserverdList );
        HotelReservation hotelReservation = new HotelReservation();
        List<Room> actual = hotelReservation.getAllAvailableRooms(RoomType.ROOM, LocalDate.of(2024,10,21),LocalDate.of(2024,10,23),reservations , 1);
        List<Room> expected = List.of(roomOne);
        assertEquals( expected, actual);
//        System.out.println("rooms = " + rooms);
    }

    @Test
    void guestWithMostReservations() {

        Room roomOne = new Room(1, 2, RoomType.ROOM, 200.00);
        Room roomTwo = new Room(2, 2, RoomType.ROOM, 200.00);
        Room roomThree = new Room(3, 2, RoomType.ROOM, 200.00);
        Guest guestOne = new Guest(1, "Frank", "gg@gg");
        Guest guestTwo = new Guest(2, "Frank", "gg@gg");

        Reservation reservationOne = new Reservation(1,LocalDate.of(2023,9,21),LocalDate.of(2023,9,22),roomOne, guestOne, 1 );
        Reservation reservationTwo = new Reservation(1,LocalDate.of(2023,9,21),LocalDate.of(2023,9,22),roomTwo, guestTwo, 2 );
        Reservation reservationThree = new Reservation(1,LocalDate.of(2023,9,21),LocalDate.of(2023,9,22),roomThree, guestTwo , 2 );

        List<Reservation> allReservations = List.of(reservationOne,reservationTwo,reservationThree);
        HotelReservation hotelReservation = new HotelReservation();
        Guest actual = hotelReservation.guestWithMostReservationsTwo(allReservations);
        assertEquals(guestTwo, actual);
    }

    @Test
    void getAllAvailableRoomsTwo(){

        Room roomOne = new Room(1, 2, RoomType.ROOM, 200.00);
        Room roomTwo = new Room(2, 2, RoomType.ROOM, 200.00);
        Room roomThree = new Room(3, 2, RoomType.ROOM, 200.00);
        Room roomFour = new Room(4, 2, RoomType.ROOM, 200.00);
        Guest guestOne = new Guest(1, "Frank", "gg@gg");
        Guest guestTwo = new Guest(2, "Frank", "gg@gg");
        Reservation reservationOne = new Reservation(1,LocalDate.of(2023,9,21),LocalDate.of(2023,9,22),roomOne, guestOne, 1 );
        Reservation reservationTwo = new Reservation(1,LocalDate.of(2023,9,21),LocalDate.of(2023,9,22),roomTwo, guestTwo, 2 );
        List<Reservation> reservations = List.of(reservationOne, reservationTwo);
        List<Room> allRooms = List.of(roomOne, roomTwo, roomThree, roomFour);
        HotelReservation hotelReservation = new HotelReservation();
        List<Room> allRoomsAvailabale = hotelReservation.getAllAvailableRoomsTwo(reservations,allRooms,LocalDate.of(2024,10,21),LocalDate.of(2024,10,23));
        List<Room> expected = List.of(roomThree, roomFour);
        System.out.println("allRoomsAvailabale = " + allRoomsAvailabale);
        assertEquals(expected, allRoomsAvailabale);
    }

    @Test
    void possibleDespiteHighestExeeded() {
        Room roomOne = new Room(1, 2, RoomType.ROOM, 200.00);
        Room roomTwo = new Room(2, 2, RoomType.ROOM, 200.00);
        Room roomThree = new Room(3, 2, RoomType.ROOM, 200.00);
        Room roomFour = new Room(4, 2, RoomType.ROOM, 200.00);
        Guest guestOne = new Guest(1, "Frank", "gg@gg");
        Guest guestTwo = new Guest(2, "Frank", "gg@gg");
        Reservation reservationOne = new Reservation(1,LocalDate.of(2023,9,21),LocalDate.of(2023,9,22),roomOne, guestOne, 1 );
        Reservation reservationTwo = new Reservation(1,LocalDate.of(2023,9,21),LocalDate.of(2023,9,22),roomTwo, guestTwo, 2 );
        List<Reservation> reservations = List.of(reservationOne, reservationTwo);
        List<Room> allRooms = List.of(roomOne, roomTwo, roomThree, roomFour);
        List<Room> expected = List.of(roomThree, roomFour);
        HotelReservation hotelReservation = new HotelReservation();
        boolean canMakereservation = hotelReservation.possibleDespiteHighestExceeded(reservations,allRooms,LocalDate.of(2024,10,21),LocalDate.of(2024,10,23), 3);
        System.out.println("canMakereservation = " + canMakereservation);
        assertTrue(canMakereservation);
    }
}