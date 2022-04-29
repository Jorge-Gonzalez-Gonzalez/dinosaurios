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
import es.cursojee.jurassicpark.controller.dto.familia.RequestCreateFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.RequestDeleteFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.RequestUpdateFamiliaDto;
import es.cursojee.jurassicpark.services.FamiliaManagementService;

@SpringBootTest(classes = Application.class)
@TestPropertySource(locations = { "classpath:application.yml" })
public class FamiliaControllerTestCase extends AbstractControllerTestCase {

	@MockBean
	private FamiliaManagementService familiaManagementService;
	
	@DisplayName("Obtiene una familia")
	@Test
	public void testFindId() throws Exception {
		String uri = "/api/familia/1001";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@DisplayName("Obtener todas las familias")
	@Test
	public void testFindAll() throws Exception {

		String uri = "/api/familia";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	
	@DisplayName("Crea una familia")
	@Test
	public void testCreate() throws Exception {

		String uri = "/api/familia";

		RequestCreateFamiliaDto familiaDTO = new RequestCreateFamiliaDto();
		familiaDTO.setNombre("Familia6");

		String inputJson = mapToJson(familiaDTO);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	

	@DisplayName("Modifica una familia")
	@Test
	public void testUpdate() throws Exception {

		String uri = "/api/familia";

		RequestUpdateFamiliaDto familiaDTO = new RequestUpdateFamiliaDto();
		familiaDTO.setNombre("Familia modificada 6");

		String inputJson = mapToJson(familiaDTO);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	
	@DisplayName("Elimina una Familia")
	@Test
	public void testDelete() throws Exception {

		String uri = "/api/familia/1005";

		RequestDeleteFamiliaDto familiaDTO = new RequestDeleteFamiliaDto();
		familiaDTO.setId(1005L);
		familiaDTO.setConfirmacion(true);

		String inputJson = mapToJson(familiaDTO);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	
}
