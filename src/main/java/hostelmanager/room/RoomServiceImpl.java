package hostelmanager.room;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hostelmanager.data.Booking;
import hostelmanager.data.Guest;
import hostelmanager.data.Room;
import hostelmanager.data.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {
	
	@Autowired
	private RoomRepository roomRepository;

	@Override
	public List<Room> getRooms() {
		return (ArrayList<Room>)roomRepository.findAll();
	}

	@Override
	public void addRoom(String name, int maxPeople, int price) {
		Room room = new Room(name,maxPeople,price);
		roomRepository.save(room);
	}

	@Override
	public void deleteRoom(Long roomId) {
		roomRepository.deleteById(roomId);

	}

	@Override
	public Room getRoom(Long roomId) {
		return roomRepository.findById(roomId).orElse(null);
	}

	@Override
	public List<Booking> getBookings(Long roomId) {
		Room room = roomRepository.findById(roomId).orElse(null);
		if(room==null) return null;
		List<Booking> bookings=room.getBookings();
		return bookings;
	}

	@Override
	public Guest getCurrentGuest(Long roomId) {
		Booking booking = getCurrentBooking(roomId);
		if(booking==null) return null;
		Guest guest = booking.getGuest();
		return guest;
	}

	@Override
	public Booking getCurrentBooking(Long roomId) {
		LocalDate currentDate = LocalDate.now();
		Room room = roomRepository.findById(roomId).orElse(null);
		if(room==null) return null;
		List<Booking> bookings = room.getBookings();
		for(Booking booking : bookings) {
			if(booking.getStartDate().isBefore(currentDate)&&booking.getEndDate().isAfter(currentDate)) return booking;
		}
		return null;
	}

}
