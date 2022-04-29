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
import es.cursojee.jurassicpark.controller.dto.familia.RequestDeleteFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.recinto.RequestCreateRecintoDto;
import es.cursojee.jurassicpark.controller.dto.recinto.RequestUpdateRecintoDto;
import es.cursojee.jurassicpark.services.RecintoManagementService;

@SpringBootTest(classes = Application.class)
@TestPropertySource(locations = { "classpath:application.yml" })
public class RecintoControllerTestCase extends AbstractControllerTestCase{
	
	@MockBean
	private RecintoManagementService recintoManagementService;
	
	@DisplayName("Obtiene un recinto")
	@Test
	public void testFindId() throws Exception {
		String uri = "/api/recinto/1001";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@DisplayName("Obtener todos los recintos")
	@Test
	public void testFindAll() throws Exception {

		String uri = "/api/recinto";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	
	@DisplayName("Crea un recinto")
	@Test
	public void testCreate() throws Exception {

		String uri = "/api/recinto";

		RequestCreateRecintoDto recintoDTO = new RequestCreateRecintoDto();
		recintoDTO.setNombre("Recinto4");
		recintoDTO.setTipoRecinto("Carnivoro");
		recintoDTO.setNumDinosaurios(0);

		String inputJson = mapToJson(recintoDTO);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	

	@DisplayName("Modifica un recinto")
	@Test
	public void testUpdate() throws Exception {

		String uri = "/api/recinto";

		RequestUpdateRecintoDto recintoDTO = new RequestUpdateRecintoDto();
		recintoDTO.setNombre("Recinto4");
		recintoDTO.setTipoRecinto("Herbívoro");
		recintoDTO.setNumDinosaurios(2);


		String inputJson = mapToJson(recintoDTO);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	
	@DisplayName("Elimina un recinto")
	@Test
	public void testDelete() throws Exception {

		String uri = "/api/recinto/1002";

		RequestDeleteFamiliaDto familiaDTO = new RequestDeleteFamiliaDto();
		familiaDTO.setId(1005L);
		familiaDTO.setConfirmacion(true);

		String inputJson = mapToJson(familiaDTO);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	
	
	
	
}
