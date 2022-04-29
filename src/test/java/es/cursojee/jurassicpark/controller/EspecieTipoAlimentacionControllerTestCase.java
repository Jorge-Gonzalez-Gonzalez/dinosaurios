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
import es.cursojee.jurassicpark.controller.dto.especieTipoAlimentacion.RequestCreateEspecieTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.especieTipoAlimentacion.RequestDeleteEspecieTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.especieTipoAlimentacion.RequestUpdateEspecieTipoAlimentacionDto;
import es.cursojee.jurassicpark.services.EspecieTipoAlimentacionManagementService;

@SpringBootTest(classes = Application.class)
@TestPropertySource(locations = { "classpath:application.yml" })
public class EspecieTipoAlimentacionControllerTestCase extends AbstractControllerTestCase{

	@MockBean
	private EspecieTipoAlimentacionManagementService especieTipoAlimentacionManagementService;
	
	@DisplayName("Obtiene especie tipo de alimentación")
	@Test
	public void testFindId() throws Exception {
		String uri = "/api/especie-tipo-alimentacion/1001";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@DisplayName("Obtener todas las especies tipos de alimentación")
	@Test
	public void testFindAll() throws Exception {

		String uri = "/api/especie-tipo-alimentacion";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	
	@DisplayName("Crea una especie tipo de alimentación")
	@Test
	public void testCreate() throws Exception {

		String uri = "/api/especie-tipo-alimentacion";

		RequestCreateEspecieTipoAlimentacionDto especieTipoAlimentacionDTO = new RequestCreateEspecieTipoAlimentacionDto();
		especieTipoAlimentacionDTO.setIdEspecie(1001L);
		especieTipoAlimentacionDTO.setIdTipoAlimentacion(1001L);

		String inputJson = mapToJson(especieTipoAlimentacionDTO);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	
	@DisplayName("Modificar una especie tipo de alimentación")
	@Test
	public void testUpdate() throws Exception {

		String uri = "/api/especie-tipo-alimentacion";

		RequestUpdateEspecieTipoAlimentacionDto especieTipoAlimentacionDTO = new RequestUpdateEspecieTipoAlimentacionDto();
		especieTipoAlimentacionDTO.setIdEspecie(1001L);
		especieTipoAlimentacionDTO.setIdTipoAlimentacion(1001L);

		String inputJson = mapToJson(especieTipoAlimentacionDTO);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	@DisplayName("Elimina una especie tipo de alimentación")
	@Test
	public void testDelete() throws Exception {

		String uri = "/api/especie-tipo-alimentacion/1003";

		RequestDeleteEspecieTipoAlimentacionDto especieTipoAlimentacionDTO = new RequestDeleteEspecieTipoAlimentacionDto();
		especieTipoAlimentacionDTO.setId(1001L);
		especieTipoAlimentacionDTO.setConfirmacion(true);
		
		String inputJson = mapToJson(especieTipoAlimentacionDTO);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
}
