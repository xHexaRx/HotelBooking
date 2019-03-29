package hostelmanager.data.repository;

import org.springframework.data.repository.CrudRepository;

import hostelmanager.data.Guest;

public interface GuestRepository extends CrudRepository<Guest, Long> {

}
