package hostelmanager;

import java.util.List;

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
import hostelmanager.room.RoomService;

@RestController("/rooms")
public class RoomRestController {
	
	@Autowired
	RoomService roomService;
	
	@GetMapping("/rooms")
	public ResponseEntity<List<Room>> getRooms(){
		List<Room> result = roomService.getRooms();
		if(result==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else return new ResponseEntity<List<Room>>(result, HttpStatus.OK);
	}
	
	@PostMapping("/rooms")
	public ResponseEntity<String> addRoom(@RequestBody Room room) {
		boolean result = roomService.addRoom(room.getName(), room.getMaxPeople(), room.getPrice());
		if(result) return new ResponseEntity<>(HttpStatus.CREATED);
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/rooms/{id}")
	public ResponseEntity<String> deleteRoom(@PathVariable("id") Long roomId) {
		roomService.deleteRoom(roomId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/rooms/{id}")
	public ResponseEntity<Room> getRoom(@PathVariable("id") Long roomId) {
		Room result = roomService.getRoom(roomId);
		if(result==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else return new ResponseEntity<Room>(result, HttpStatus.OK);
	}
	
	@GetMapping("/rooms/{id}/bookings")
	public ResponseEntity<List<Booking>> getBookings(@PathVariable("id") Long roomId){
		List<Booking> result = roomService.getBookings(roomId);
		if(result==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else return new ResponseEntity<List<Booking>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/rooms/{id}/guest")
	public ResponseEntity<Guest> getCurrentGuest(@PathVariable("id") Long roomId) {
		Guest result = roomService.getCurrentGuest(roomId);
		if(result==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else return new ResponseEntity<Guest>(result, HttpStatus.OK);
	}
	
	@GetMapping("/rooms/{id}/booking")
	public ResponseEntity<Booking> getCurrentBooking(@PathVariable("id") Long roomId) {
		Booking result = roomService.getCurrentBooking(roomId);
		if(result==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else return new ResponseEntity<Booking>(result, HttpStatus.OK);
	}

}
