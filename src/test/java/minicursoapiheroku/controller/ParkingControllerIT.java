package minicursoapiheroku.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import minicursoapiheroku.controller.dto.ParkingCreateDTO;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingControllerIT {

	@LocalServerPort
	private int randomPort;
	
	@BeforeEach
	public void setUpTest() {
		RestAssured.port = randomPort;
	}
	
	
	@Test
	void whenfindAllThenCheckResult() {
		RestAssured.given().when().get("/parking")
		.then().body("license[0]", Matchers.equalTo("XXX-000"));
	}
	
	@Test
	void whenCreateThenCheckIsCreated(){
		ParkingCreateDTO createDto = new ParkingCreateDTO();
		createDto.setColor("Amarelo");
		createDto.setLicense("xxx-020");
		createDto.setModel("Brasilia");
		createDto.setState("SP");
		RestAssured.given().when().contentType(MediaType.APPLICATION_JSON_VALUE).body(createDto).post("/post").then()
		.statusCode(200).body("license",Matchers.equalTo("xxx-020"));
	}
	
	
}
