package hostelmanager.guest;

import java.util.List;

import hostelmanager.data.Booking;
import hostelmanager.data.Guest;
import hostelmanager.data.Room;

public interface GuestService {
	public List<Guest> getGuests();
	public Guest getGuest(Long guestId);
	public void addGuest(String name, String email, String phoneNumber);
	public void deleteGuest(Long guestId);
	public List<Booking> getBookings(Long guestId);
	public Room getCurrentRoom(Long guestId);
	public Booking getCurrentBooking(Long guestId);
}
