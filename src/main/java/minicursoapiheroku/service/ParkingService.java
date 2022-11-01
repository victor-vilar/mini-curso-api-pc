package minicursoapiheroku.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import minicursoapiheroku.model.Parking;

@Service
public class ParkingService {

	private static Map<String,Parking> parkingMap = new HashMap();
	
	
	static {
		String id = getUUID();
		Parking parking = new Parking(id,"XXX-000","SC","CELTA","BRANCO");
		parkingMap.put(id, parking);
	}
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public List<Parking> findAll(){
		return parkingMap.values().stream().collect(Collectors.toList());
	}
	
}
