package hostelmanager.data;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BookingRequest {
	private LocalDate startDate;
	private LocalDate endDate;
	private Long roomId;
	private Long guestId;
}
