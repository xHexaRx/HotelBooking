package hostelmanager.data.repository;

import org.springframework.data.repository.CrudRepository;

import hostelmanager.data.Booking;

public interface BookingRepository extends CrudRepository<Booking, Long>{

}
