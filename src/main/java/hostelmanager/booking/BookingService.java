package hostelmanager.booking;

import java.time.LocalDate;
import java.util.List;

import hostelmanager.data.Booking;
import hostelmanager.data.Guest;
import hostelmanager.data.Room;

public interface BookingService {
	public boolean book(LocalDate startDate, LocalDate endDate, Long roomId, Long guestId);
	public List<Room> getAvaliableRooms(LocalDate startDate, LocalDate endDate);
	public void deleteBooking(Long bookingId);
	public List<Booking> getBooking();
	public Booking getBooking(Long bookingId);
	public Guest getGuest(Long bookingId);
	public Room getRoom(Long bookingId);
}
