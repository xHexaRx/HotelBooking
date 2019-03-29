package hostelmanager.data;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Booking {
	
	@Id	@GeneratedValue	private Long bookingId;
	private LocalDate startDate;
	private LocalDate endDate;
	@ManyToOne @JoinColumn(name="roomId") private Room room;
	@ManyToOne @JoinColumn(name="guestId") private Guest guest;
	
	public Booking(LocalDate startDate, LocalDate endDate, Guest guest, Room room) {
		this.startDate=startDate;
		this.endDate=endDate;
		this.guest=guest;
		this.room=room;
	}

}
