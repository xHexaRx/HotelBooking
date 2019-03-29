package hostelmanager;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hostelmanager.booking.BookingService;
import hostelmanager.data.Room;

@RestController("/bookings")
public class BookingRestController {
	
	@Autowired
	BookingService bookingService;
	
	@GetMapping("/rooms")
	public List<Room> getRooms(@RequestParam("startDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam("endDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate endDate){
		return bookingService.getAvaliableRooms(startDate, endDate);
	}
	
	@GetMapping
	public void book(@RequestParam("startDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam("endDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate endDate,
			@RequestParam("roomId") Long roomId,
			@RequestParam("guestId") Long guestId) {
		bookingService.book(startDate, endDate, roomId, guestId);		
	}
}