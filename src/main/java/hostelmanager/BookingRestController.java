package hostelmanager;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<List<Room>> getRooms(@RequestParam("startDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam("endDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate endDate){
		List<Room> response = bookingService.getAvaliableRooms(startDate, endDate);
		if(response==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else return new ResponseEntity<List<Room>>(response, HttpStatus.OK);
	}
	
	
	/**
	 * Create new booking.
	 * @param booking requires BookingRequest object(LocalDate startDate, LocalDate endDate, Long roomId, Long guestId).
	 */
	@PostMapping("/bookings")
	public ResponseEntity<String> book(@RequestBody BookingRequest booking) {
		boolean result = bookingService.book(booking.getStartDate(), booking.getEndDate(), booking.getRoomId(), booking.getGuestId());
		if(result) return new ResponseEntity<>(HttpStatus.CREATED); 
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
	}
	
	@DeleteMapping("/bookings/{id}")
	public ResponseEntity<String> deleteBooking(@PathVariable("id") Long id) {
		bookingService.deleteBooking(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/bookings")
	public ResponseEntity<List<Booking>> getBooking(){
		List<Booking> result = bookingService.getBookings();
		if(result==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else return new ResponseEntity<List<Booking>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/bookings/{id}")
	public ResponseEntity<Booking> getBooking(@PathVariable("id") Long id) {
		Booking result = bookingService.getBooking(id);
		if(result==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else return new ResponseEntity<Booking>(result, HttpStatus.OK);
	}
	
	@GetMapping("/bookings/{id}/room")
	public ResponseEntity<Room> getRoom(@PathVariable("id") Long bookingId) {
		Room result = bookingService.getRoom(bookingId);
		if(result==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else return new ResponseEntity<Room>(result, HttpStatus.OK);
	}
	
	@GetMapping("/bookings/{id}/guest")
	public ResponseEntity<Guest> getGuest(@PathVariable("id") Long bookingId) {
		Guest result = bookingService.getGuest(bookingId);
		if(result==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else return new ResponseEntity<Guest>(result, HttpStatus.OK);
	}
}