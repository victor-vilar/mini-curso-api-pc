package minicursoapiheroku.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import minicursoapiheroku.controller.dto.ParkingCreateDTO;
import minicursoapiheroku.controller.dto.ParkingDTO;
import minicursoapiheroku.model.Parking;

@Component
public class ParkingMapper {

	private static final ModelMapper MODEL_MAPPER = new ModelMapper();
	
	public ParkingDTO toParkingDto(Parking parking) {
		return MODEL_MAPPER.map(parking, ParkingDTO.class);
	}
	
	public List<ParkingDTO> toParkingDTOList(List<Parking> parkingList) {
		return parkingList.stream().map(this::toParkingDto).collect(Collectors.toList());
	}

	public Parking toParking(ParkingDTO parkingDto) {
		return MODEL_MAPPER.map(parkingDto, Parking.class);
	}
	
	public Parking toParkingCreate(ParkingCreateDTO parkingDto) {
		return MODEL_MAPPER.map(parkingDto, Parking.class);
	}

	
}
