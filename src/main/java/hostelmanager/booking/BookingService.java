package hostelmanager.booking;

import java.time.LocalDate;
import java.util.List;

import hostelmanager.data.Room;

public interface BookingService {
	public boolean book(LocalDate startDate, LocalDate endDate, Long roomId, Long guestId);
	public List<Room> getAvaliableRooms(LocalDate startDate, LocalDate endDate);
	public void deleteBooking(Long bookingId);
}
