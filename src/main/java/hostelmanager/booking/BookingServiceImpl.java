package hostelmanager.booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hostelmanager.data.Booking;
import hostelmanager.data.Guest;
import hostelmanager.data.Room;
import hostelmanager.data.repository.BookingRepository;
import hostelmanager.data.repository.GuestRepository;
import hostelmanager.data.repository.RoomRepository;

@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private GuestRepository guestRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	public boolean roomIsAvaliable(LocalDate startDate, LocalDate endDate, Room room) {
		boolean isAvaliable = true;
		List<Booking> bookings = room.getBookings();
		for(Booking booking : bookings) {
			if(startDate.isBefore(booking.getEndDate())&&endDate.isAfter(booking.getStartDate())){
				isAvaliable=false;
			}
		}
		return isAvaliable;
	}

	@Override
	public boolean book(LocalDate startDate, LocalDate endDate, Long roomId, Long guestId) {
		Room room = roomRepository.findById(roomId).orElse(null);
		Guest guest = guestRepository.findById(guestId).orElse(null);
		if(roomIsAvaliable(startDate, endDate, room)) {
			Booking booking = new Booking(startDate, endDate, guest, room);
			bookingRepository.save(booking);
		}
		return true;
	}

	@Override
	public List<Room> getAvaliableRooms(LocalDate startDate, LocalDate endDate) {
		List<Room> avaliableRooms = new ArrayList<Room>();
		Iterable<Room> rooms = roomRepository.findAll();
		for(Room room : rooms) {
			if(roomIsAvaliable(startDate, endDate, room)) avaliableRooms.add(room);
		}
		return avaliableRooms;
	}

	@Override
	public void deleteBooking(Long bookingId) {
		bookingRepository.deleteById(bookingId);
	}

}
