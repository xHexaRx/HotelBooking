package hostelmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public List<Guest> getGuests(){
		return guestService.getGuests();
	}
	
	@GetMapping("/guests/{id}")
	public Guest getGuest(@PathVariable("id") Long guestId) {
		return guestService.getGuest(guestId);
	}
	
	@PostMapping("/guests")
	public void addGuest(String name, String email, String phoneNumber) {
		guestService.addGuest(name, email, phoneNumber);
	}
	
	@DeleteMapping("/guests/{id}")
	public void deleteGuest(@PathVariable("id") Long guestId) {
		guestService.deleteGuest(guestId);
	}
	
	@GetMapping("/guests/{id}/bookings")
	public List<Booking> getBookings(@PathVariable("id") Long guestId){
		return guestService.getBookings(guestId);
	}
	
	@GetMapping("/guests/{id}/room")
	public Room getCurrentRoom(@PathVariable("ïd") Long guestId) {
		return guestService.getCurrentRoom(guestId);
	}
	
	@GetMapping("/guests/{id}/booking")
	public Booking getCurrentBooking(@PathVariable("id") Long guestId) {
		return guestService.getCurrentBooking(guestId);
	}

}
