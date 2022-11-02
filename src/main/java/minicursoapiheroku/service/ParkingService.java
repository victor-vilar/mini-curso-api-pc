package minicursoapiheroku.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import minicursoapiheroku.exception.ParkingNotFoundException;
import minicursoapiheroku.model.Parking;

@Service
public class ParkingService {

	private static Map<String,Parking> parkingMap = new HashMap();
	
	
	static {
		String id = getUUID();
		String id1 = getUUID();
		Parking parking = new Parking(id,"XXX-000","SC","CELTA","BRANCO");
		Parking parking2 = new Parking(id1,"XXX-111","SP","GOL","VERDE");
		parkingMap.put(id, parking);
		parkingMap.put(id1, parking2);
	}
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public List<Parking> findAll(){
		return parkingMap.values().stream().collect(Collectors.toList());
	}
	
	public Parking findById(String id) {
		
		Parking parking =parkingMap.get(id);
		if(parking == null) {
			throw new ParkingNotFoundException(id);
		}
		return parking;
	}

	public Parking create(Parking parkingCreate) {
		parkingCreate.setId(getUUID());
		parkingCreate.setEntryDate(LocalDateTime.now());
		parkingMap.put(parkingCreate.getId(), parkingCreate);
		return parkingCreate;
	}

	public void deleteById(String id) {
		findById(id);
		parkingMap.remove(id);
		
	}

	public Parking update(Parking parkingCreate, String id) {
		Parking parking = findById(id);
		parking.setColor(parkingCreate.getColor());
		parkingMap.replace(id, parking);
		return parking;
	}
	
}
