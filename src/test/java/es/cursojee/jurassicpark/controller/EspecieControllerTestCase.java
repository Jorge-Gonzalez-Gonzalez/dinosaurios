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
import es.cursojee.jurassicpark.controller.dto.especie.RequestCreateEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.RequestDeleteEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.RequestUpdateEspecieDto;
import es.cursojee.jurassicpark.model.tipoPeligrosidad.CodigoTipoPeligrosidad;
import es.cursojee.jurassicpark.services.EspecieManagementService;

@SpringBootTest(classes = Application.class)
@TestPropertySource(locations = { "classpath:application.yml" })
public class EspecieControllerTestCase extends AbstractControllerTestCase {

	@MockBean
	private EspecieManagementService especieManagementService;
	
	@DisplayName("Obtiene una especie")
	@Test
	public void testFindId() throws Exception {
		String uri = "/api/especie/1002";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@DisplayName("Obtener todas las especies")
	@Test
	public void testFindAll() throws Exception {

		String uri = "/api/especie";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	
	@DisplayName("Crea una especie")
	@Test
	public void testCreate() throws Exception {

		String uri = "/api/especie";

		RequestCreateEspecieDto especieDTO = new RequestCreateEspecieDto();
		especieDTO.setNombre("Nueva Especie");
		especieDTO.setIdFamilia(1001L);
		especieDTO.setCodigoTipoPeligrosidad(CodigoTipoPeligrosidad.BAJA);
		especieDTO.setLongitud(50);

		String inputJson = mapToJson(especieDTO);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	
	@DisplayName("Modifica una especie")
	@Test
	public void testUpdate() throws Exception {

		String uri = "/api/especie";

		RequestUpdateEspecieDto especieDTO = new RequestUpdateEspecieDto();
		especieDTO.setNombre("Especie Modificada");
		especieDTO.setIdFamilia(1001L);
		especieDTO.setCodigoTipoPeligrosidad(CodigoTipoPeligrosidad.ALTA);
		especieDTO.setLongitud(50);

		String inputJson = mapToJson(especieDTO);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	
	@DisplayName("Elimina una especie")
	@Test
	public void testDelete() throws Exception {

		String uri = "/api/especie/1005";

		RequestDeleteEspecieDto especieDTO = new RequestDeleteEspecieDto();
		especieDTO.setId(1005L);
		especieDTO.setConfirmacion(true);

		String inputJson = mapToJson(especieDTO);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
		
}
