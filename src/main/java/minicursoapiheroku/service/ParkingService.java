package minicursoapiheroku.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import minicursoapiheroku.exception.ParkingNotFoundException;
import minicursoapiheroku.model.Parking;
import minicursoapiheroku.repository.ParkingRepository;

@Service
public class ParkingService {

	private final ParkingRepository parkingRepository;
	
	public ParkingService(ParkingRepository parkingRepository) {
		this.parkingRepository = parkingRepository;
	}
	
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public List<Parking> findAll(){
		return parkingRepository.findAll();
	}
	
	public Parking findById(String id) {
		
		return parkingRepository.findById(id).orElseThrow(( )->
		new ParkingNotFoundException(id));
		
	}

	public Parking create(Parking parkingCreate) {
		parkingCreate.setId(getUUID());
		parkingCreate.setEntryDate(LocalDateTime.now());
		parkingRepository.save(parkingCreate);
		return parkingCreate;
	}

	public void deleteById(String id) {
		findById(id);
		parkingRepository.deleteById(id);
		
	}

	public Parking update(Parking parkingCreate, String id) {
		Parking parking = findById(id);
		parking.setColor(parkingCreate.getColor());
		parking.setState(parkingCreate.getState());
		parking.setModel(parkingCreate.getModel());
		parking.setLicense(parkingCreate.getLicense());
		this.parkingRepository.save(parking);
		return parking;
	}

	public Parking exitParking(String id) {
		Parking parking = findById(id);
		parking.setExitDate(LocalDateTime.now());
		parking.setBill(50.00);
		this.parkingRepository.save(parking);
		return parking;
	}
	
}
