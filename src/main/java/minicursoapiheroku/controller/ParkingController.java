package minicursoapiheroku.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import minicursoapiheroku.controller.dto.ParkingDTO;
import minicursoapiheroku.controller.mapper.ParkingMapper;
import minicursoapiheroku.model.Parking;
import minicursoapiheroku.service.ParkingService;


@RestController
@RequestMapping("/parking")
public class ParkingController {

	
	private final ParkingService parkingService;
	private final ParkingMapper parkingMapper;
	
	public ParkingController(ParkingService parkingservice, ParkingMapper parkingMappger) {
		this.parkingService =parkingservice;
		this.parkingMapper = parkingMappger;
	}
	
	@GetMapping()
	public List<ParkingDTO> findAll(){
		List<Parking> parkingList = this.parkingService.findAll();
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
		return result;
	}
}
