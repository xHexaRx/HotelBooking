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
import hostelmanager.data.Room;

@RestController("/bookings")
public class BookingRestController {
	
	@Autowired
	BookingService bookingService;
	
	@GetMapping("/bookings/rooms")
	public List<Room> getRooms(@RequestParam("startDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam("endDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate endDate){
		return bookingService.getAvaliableRooms(startDate, endDate);
	}
	
	@PostMapping("/bookings")
	public void book(@RequestBody BookingRequest booking) {
		bookingService.book(booking.getStartDate(), booking.getEndDate(), booking.getRoomId(), booking.getGuestId());		
	}
	
	@DeleteMapping("/bookings/{id}")
	public void deleteBooking(@PathVariable("id") Long id) {
		deleteBooking(id);
	}
}