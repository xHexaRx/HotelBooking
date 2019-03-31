package hostelmanager;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hostelmanager.booking.BookingService;
import hostelmanager.data.Booking;
import hostelmanager.data.BookingRequest;
import hostelmanager.data.Guest;
import hostelmanager.data.Room;

@RestController("/bookings")
public class BookingRestController {
	
	@Autowired
	BookingService bookingService;
	
	@GetMapping("/bookings/avaliableRooms")
	public List<Room> getRooms(@RequestParam("startDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam("endDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate endDate){
		return bookingService.getAvaliableRooms(startDate, endDate);
	}
	
	
	/**
	 * Create new booking.
	 * @param booking requires BookingRequest object(LocalDate startDate, LocalDate endDate, Long roomId, Long guestId).
	 */
	@PostMapping("/bookings")
	public void book(@RequestBody BookingRequest booking) {
		bookingService.book(booking.getStartDate(), booking.getEndDate(), booking.getRoomId(), booking.getGuestId());		
	}
	
	@DeleteMapping("/bookings/{id}")
	public void deleteBooking(@PathVariable("id") Long id) {
		deleteBooking(id);
	}
	
	@GetMapping("/bookings")
	public List<Booking> getBooking(){
		return bookingService.getBookings();
	}
	
	@GetMapping("/bookings/{id}")
	public Booking getBooking(@PathVariable("id") Long id) {
		return bookingService.getBooking(id);
	}
	
	@GetMapping("/bookings/{id}/room")
	public Room getRoom(@PathVariable("id") Long bookingId) {
		return bookingService.getRoom(bookingId);
	}
	
	@GetMapping("/bookings/{id}/guest")
	public Guest getGuest(@PathVariable("id") Long bookingId) {
		return bookingService.getGuest(bookingId);
	}
}