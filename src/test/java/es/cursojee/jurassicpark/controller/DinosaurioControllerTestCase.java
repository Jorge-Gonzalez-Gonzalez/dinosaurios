package es.cursojee.jurassicpark.controller;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import es.cursojee.jurassicpark.Application;
import es.cursojee.jurassicpark.controller.dto.dinosaurio.RequestCreateDinosaurioDto;
import es.cursojee.jurassicpark.controller.dto.dinosaurio.RequestDeleteDinosaurioDto;
import es.cursojee.jurassicpark.controller.dto.dinosaurio.RequestUpdateDinosaurioDto;
import es.cursojee.jurassicpark.services.DinosaurioManagementService;

@SpringBootTest(classes = Application.class)
@TestPropertySource(locations = { "classpath:application.yml" })
public class DinosaurioControllerTestCase extends AbstractControllerTestCase {

	@MockBean
	private DinosaurioManagementService dinosaurioManagementService;
	
	@DisplayName("Obtiene un dinosaurio")
	@Test
	public void testFindId() throws Exception {
		String uri = "/api/dinosaurio/1001";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@DisplayName("Obtener todas los dinosaurios")
	@Test
	public void testFindAll() throws Exception {

		String uri = "/api/dinosaurio";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	
	@DisplayName("Crea un dinosaurio")
	@Test
	public void testCreate() throws Exception {

		String uri = "/api/dinosaurio";

		RequestCreateDinosaurioDto dinosaurioDTO = new RequestCreateDinosaurioDto();
		dinosaurioDTO.setNombre("nuevo Dinosaurio");
		dinosaurioDTO.setIdEspecie(1001L);
		dinosaurioDTO.setIdRecinto(1003L);

		String inputJson = mapToJson(dinosaurioDTO);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	

	@DisplayName("Modifica una dinosaurio")
	@Test
	public void testUpdate() throws Exception {

		String uri = "/api/dinosaurio";

		RequestUpdateDinosaurioDto dinosaurioDTO = new RequestUpdateDinosaurioDto();
		dinosaurioDTO.setNombre("nuevo Dinosaurio Modificado");
		dinosaurioDTO.setIdEspecie(1001L);
		dinosaurioDTO.setIdRecinto(1003L);

		String inputJson = mapToJson(dinosaurioDTO);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	
	@DisplayName("Elimina un dinosaurio")
	@Test
	public void testDelete() throws Exception {

		String uri = "/api/dinosaurio/1005";

		RequestDeleteDinosaurioDto dinosaurioDTO = new RequestDeleteDinosaurioDto();
		dinosaurioDTO.setId(1005L);
		dinosaurioDTO.setConfirmacion(true);

		String inputJson = mapToJson(dinosaurioDTO);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	
}
