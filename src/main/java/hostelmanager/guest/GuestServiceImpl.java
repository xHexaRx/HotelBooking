package hostelmanager.guest;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hostelmanager.data.Booking;
import hostelmanager.data.Guest;
import hostelmanager.data.Room;
import hostelmanager.data.repository.GuestRepository;

@Service
public class GuestServiceImpl implements GuestService {
	
	@Autowired
	private GuestRepository guestRepository;
	
	@Override
	public List<Guest> getGuests() {
		return (ArrayList<Guest>)guestRepository.findAll();
	}

	@Override
	public Guest getGuest(Long guestId) {
		return guestRepository.findById(guestId).orElse(null);
	}

	@Override
	public boolean addGuest(String name, String email, String phoneNumber) {
		Guest guest = new Guest(name,email,phoneNumber);
		guestRepository.save(guest);
		return true;
	}

	@Override
	public void deleteGuest(Long guestId) {
		guestRepository.deleteById(guestId);
	}

	@Override
	public List<Booking> getBookings(Long guestId) {
		Guest guest = guestRepository.findById(guestId).orElse(null);
		if(guest==null) return null;
		List<Booking> bookings = guest.getBookings();
		return bookings;
	}

	@Override
	public Room getCurrentRoom(Long guestId) {
		Booking booking = getCurrentBooking(guestId);
		Room room = booking.getRoom();
		return room;
	}

	@Override
	public Booking getCurrentBooking(Long guestId) {
		LocalDate currentDate = LocalDate.now();
		Guest guest = guestRepository.findById(guestId).orElse(null);
		if(guest==null) return null;
		List<Booking> bookings = guest.getBookings();
		for(Booking booking : bookings) {
			if(booking.getStartDate().isBefore(currentDate)&&booking.getEndDate().isAfter(currentDate)) return booking;
		}
		return null;
	}

}
