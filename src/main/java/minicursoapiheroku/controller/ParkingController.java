package minicursoapiheroku.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import minicursoapiheroku.controller.dto.ParkingCreateDTO;
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
	public ResponseEntity<List<ParkingDTO>> findAll(){
		List<Parking> parkingList = this.parkingService.findAll();
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ParkingDTO> findById(@PathVariable String id){
		ParkingDTO result = parkingMapper.toParkingDto(this.parkingService.findById(id));
		return ResponseEntity.ok(result);
	}
	
	@PostMapping()
	public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO parkingDto){
		Parking parkingCreate = this.parkingMapper.toParkingCreate(parkingDto);
		Parking parking = parkingService.create(parkingCreate);
		ParkingDTO parkingdtoo =  this.parkingMapper.toParkingDto(parking);
		return ResponseEntity.status(HttpStatus.CREATED).body(parkingdtoo);
	}
}
