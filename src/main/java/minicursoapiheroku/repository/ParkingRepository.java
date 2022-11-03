package minicursoapiheroku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import minicursoapiheroku.model.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, String> {

}
