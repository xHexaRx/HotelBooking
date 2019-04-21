package hostelmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hostelmanager.data.Booking;
import hostelmanager.data.Guest;
import hostelmanager.data.Room;
import hostelmanager.guest.GuestService;
import java.util.List;

@RestController("/guests")
public class GuestRestController {
	
	@Autowired
	GuestService guestService;
	
	@GetMapping("/guests")
	public ResponseEntity<List<Guest>> getGuests(){
		List<Guest> result = guestService.getGuests();
		if(result==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else return new ResponseEntity<List<Guest>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/guests/{id}")
	public ResponseEntity<Guest> getGuest(@PathVariable("id") Long guestId) {
		Guest result = guestService.getGuest(guestId);
		if(result==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else return new ResponseEntity<Guest>(result, HttpStatus.OK);
	}
	
	@PostMapping("/guests")
	public ResponseEntity<String> addGuest(@RequestBody Guest guest) {
		boolean result = guestService.addGuest(guest.getName(), guest.getEmail(), guest.getPhoneNumber());
		if(result) return new ResponseEntity<>(HttpStatus.CREATED);
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/guests/{id}")
	public ResponseEntity<String> deleteGuest(@PathVariable("id") Long guestId) {
		guestService.deleteGuest(guestId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/guests/{id}/bookings")
	public ResponseEntity<List<Booking>> getBookings(@PathVariable("id") Long guestId){
		List<Booking> result = guestService.getBookings(guestId);
		if(result==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else return new ResponseEntity<List<Booking>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/guests/{id}/room")
	public ResponseEntity<Room> getCurrentRoom(@PathVariable("ïd") Long guestId) {
		Room result = guestService.getCurrentRoom(guestId);
		if(result==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else return new ResponseEntity<Room>(result, HttpStatus.OK);
	}
	
	@GetMapping("/guests/{id}/booking")
	public ResponseEntity<Booking> getCurrentBooking(@PathVariable("id") Long guestId) {
		Booking result = guestService.getCurrentBooking(guestId);
		if(result==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else return new ResponseEntity<Booking>(result, HttpStatus.OK);
	}

}
