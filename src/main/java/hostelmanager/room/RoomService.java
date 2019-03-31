package hostelmanager.room;

import java.util.List;

import hostelmanager.data.Booking;
import hostelmanager.data.Guest;
import hostelmanager.data.Room;

public interface RoomService {
	public List<Room> getRooms();
	public void addRoom(String name, int maxPeople, int price);
	public void deleteRoom(Long roomId);
	public Room getRoom(Long roomId);
	public List<Booking> getBookings(Long roomId);
	public Guest getCurrentGuest(Long roomId);
	public Booking getCurrentBooking(Long roomId);
}
