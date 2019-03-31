package hostelmanager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hostelmanager.data.Booking;
import hostelmanager.data.Guest;
import hostelmanager.data.Room;
import hostelmanager.room.RoomService;

@RestController("/rooms")
public class RoomRestController {
	
	@Autowired
	RoomService roomService;
	
	@GetMapping("/rooms")
	public List<Room> getRooms(){
		return roomService.getRooms();
	}
	
	@PostMapping("/rooms")
	public void addRoom(@RequestBody Room room) {
		roomService.addRoom(room.getName(), room.getMaxPeople(), room.getPrice());
	}
	
	@DeleteMapping("/rooms/{id}")
	public void deleteRoom(@PathVariable("id") Long roomId) {
		roomService.deleteRoom(roomId);
	}
	
	@GetMapping("/rooms/{id}")
	public Room getRoom(@PathVariable("id") Long roomId) {
		return roomService.getRoom(roomId);
	}
	
	@GetMapping("/rooms/{id}/bookings")
	public List<Booking> getBookings(@PathVariable("id") Long roomId){
		return roomService.getBookings(roomId);
	}
	
	@GetMapping("/rooms/{id}/guest")
	public Guest getCurrentGuest(@PathVariable("id") Long roomId) {
		return roomService.getCurrentGuest(roomId);
	}
	
	@GetMapping("/rooms/{id}/booking")
	public Booking getCurrentBooking(@PathVariable("id") Long roomId) {
		return roomService.getCurrentBooking(roomId);
	}

}
