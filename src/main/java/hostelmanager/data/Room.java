package hostelmanager.data;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Room {
	
	@Id	@GeneratedValue	private Long roomId;
	private String name;
	private int maxPeople;
	private int price;	
	@OneToMany(mappedBy="room")	private List<Booking> bookings;
	
	public Room(String name, int maxPeople, int price) {
		this.name=name;
		this.maxPeople=maxPeople;
		this.price=price;
	}
	
}
