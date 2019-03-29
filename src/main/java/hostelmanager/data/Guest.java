package hostelmanager.data;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Guest {
	
	@Id	@GeneratedValue	private Long guestId;	
	private String name;
	private String email;
	private String phoneNumber;
	@OneToMany(mappedBy="guest")private List<Booking> bookings;
	
	public Guest(String name, String email, String phoneNumber) {
		this.name=name;
		this.email=email;
		this.phoneNumber=phoneNumber;
	}
	
}
