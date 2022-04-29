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
import es.cursojee.jurassicpark.controller.dto.tipoAlimentacion.RequestCreateTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.tipoAlimentacion.RequestDeleteTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.tipoAlimentacion.RequestUpdateTipoAlimentacionDto;
import es.cursojee.jurassicpark.services.TipoAlimentacionManagementService;

@SpringBootTest(classes = Application.class)
@TestPropertySource(locations = { "classpath:application.yml" })
public class TipoAlimentacionControllerTestCase extends AbstractControllerTestCase {

	@MockBean
	private TipoAlimentacionManagementService tipoAlimentacionManagementService;
	
	@DisplayName("Obtiene una especie tipo de alimentación")
	@Test
	public void testFindId() throws Exception {
		String uri = "/api/tipo-alimentacion/1001";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@DisplayName("Obtener todas las especies tipos de alimentación")
	@Test
	public void testFindAll() throws Exception {

		String uri = "/api/tipo-alimentacion";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	
	@DisplayName("Crea una especie  tipo de alimentación")
	@Test
	public void testCreate() throws Exception {

		String uri = "/api/tipo-alimentacion";

		RequestCreateTipoAlimentacionDto tipoAlimentacionDTO = new RequestCreateTipoAlimentacionDto();
		tipoAlimentacionDTO.setDescripcion("Huevos");

		String inputJson = mapToJson(tipoAlimentacionDTO);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	
	@DisplayName("Modificar una especie tipo de alimentación")
	@Test
	public void testUpdate() throws Exception {

		String uri = "/api/tipo-alimentacion";

		RequestUpdateTipoAlimentacionDto tipoAlimentacionDTO = new RequestUpdateTipoAlimentacionDto();
		tipoAlimentacionDTO.setId(1001L);
		tipoAlimentacionDTO.setDescripcion("Huevos");

		String inputJson = mapToJson(tipoAlimentacionDTO);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	@DisplayName("Elimina una especie tipo de alimentación")
	@Test
	public void testDelete() throws Exception {

		String uri = "/api/tipo-alimentacion/1003";

		RequestDeleteTipoAlimentacionDto tipoAlimentacionDTO = new RequestDeleteTipoAlimentacionDto();
		tipoAlimentacionDTO.setId(1001L);
		tipoAlimentacionDTO.setConfirmacion(true);
		
		String inputJson = mapToJson(tipoAlimentacionDTO);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
}
