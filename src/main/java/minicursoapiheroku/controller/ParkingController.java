package minicursoapiheroku.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import minicursoapiheroku.controller.dto.ParkingCreateDTO;
import minicursoapiheroku.controller.dto.ParkingDTO;
import minicursoapiheroku.controller.mapper.ParkingMapper;
import minicursoapiheroku.model.Parking;
import minicursoapiheroku.service.ParkingService;


@RestController
@RequestMapping("/parking")
@Api(tags="Parking Controller")
public class ParkingController {

	
	private final ParkingService parkingService;
	private final ParkingMapper parkingMapper;
	
	public ParkingController(ParkingService parkingservice, ParkingMapper parkingMappger) {
		this.parkingService =parkingservice;
		this.parkingMapper = parkingMappger;
	}
	
	@GetMapping()
	@ApiOperation("Find all Parkings")
	public ResponseEntity<List<ParkingDTO>> findAll(){
		List<Parking> parkingList = this.parkingService.findAll();
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Find a Parking by Id")
	public ResponseEntity<ParkingDTO> findById(@PathVariable String id){
		ParkingDTO result = parkingMapper.toParkingDto(this.parkingService.findById(id));
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("Delete a Parking by Id")
	public ResponseEntity deleteById(@PathVariable String id){
		this.parkingService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	@ApiOperation("Update a Parking")
	public ResponseEntity<ParkingDTO> update(@RequestBody ParkingCreateDTO parkingDto, @PathVariable String id){
		Parking parkingCreate = this.parkingMapper.toParkingCreate(parkingDto);
		Parking parking = parkingService.update(parkingCreate,id);
		ParkingDTO parkingdtoo =  this.parkingMapper.toParkingDto(parking);
		return ResponseEntity.status(HttpStatus.OK).body(parkingdtoo);
	}

	
	@PostMapping()
	@ApiOperation("Create a new Parking")
	public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO parkingDto){
		Parking parkingCreate = this.parkingMapper.toParkingCreate(parkingDto);

		Parking parking = parkingService.create(parkingCreate);
		ParkingDTO parkingdtoo =  this.parkingMapper.toParkingDto(parking);
		return ResponseEntity.status(HttpStatus.CREATED).body(parkingdtoo);
	}
}
