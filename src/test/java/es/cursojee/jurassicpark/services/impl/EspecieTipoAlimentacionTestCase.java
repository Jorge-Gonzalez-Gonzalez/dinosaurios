package es.cursojee.jurassicpark.services.impl;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import es.cursojee.jurassicpark.Application;
import es.cursojee.jurassicpark.controller.dto.especieTipoAlimentacion.RequestCreateEspecieTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.especieTipoAlimentacion.RequestDeleteEspecieTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.especieTipoAlimentacion.RequestUpdateEspecieTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.especieTipoAlimentacion.ResponseEspecieTipoAlimentacionDto;
import es.cursojee.jurassicpark.exception.DinosaurioElementNotFoundException;
import es.cursojee.jurassicpark.exception.NotConfirmDeleteDinosaurio;
import es.cursojee.jurassicpark.services.EspecieTipoAlimentacionManagementService;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@TestPropertySource(locations = "classpath:application-test.yml")
public class EspecieTipoAlimentacionTestCase extends AbstractServiceTestCase {
	
	@Autowired
	private EspecieTipoAlimentacionManagementService especieTipoAlimentacionManagementService;
	
	@Test
	@DisplayName("Obtener todas las especies tipos alimnetaciones de dinosaurio existentes")
	public void testFindAll() {
		
		List<ResponseEspecieTipoAlimentacionDto> especieTipoAlimentacion = especieTipoAlimentacionManagementService.findAll();
		System.out.println(especieTipoAlimentacion.size());
		
		assertNotNull(especieTipoAlimentacion);
		assertEquals(5,especieTipoAlimentacion.size());
	}
	
	@Test
	@DisplayName("Obtener una especie tipo alimentacion existente")
	public void testFindByIdExist() {
		try {
			ResponseEspecieTipoAlimentacionDto response = especieTipoAlimentacionManagementService.findById(1001L);
			assertNotNull(response);
			assertEquals(1001,response.getId().longValue());
			assertEquals(1001, response.getIdEspecie().longValue());
			assertEquals(1001, response.getIdTipoAlimentacion().longValue());
		} catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("Se esperaba que existiene EspecieTipoAlimentacion");
		}
	}
	
	@Test
	@DisplayName("Obtener una especie tipo Alimentacion no existente")
	public void testFindByIdNotExist() {

		try {
			ResponseEspecieTipoAlimentacionDto response = especieTipoAlimentacionManagementService.findById(10L);
			fail("Se esperaba que el elemento no existiera");
		} catch (DinosaurioElementNotFoundException e) {
			
		}

	}
	
	@Test
	@DisplayName("Crea una especie tipo alimentacion")
	public void testCreate() {
		RequestCreateEspecieTipoAlimentacionDto newEspecieTipoAlimentacion = new RequestCreateEspecieTipoAlimentacionDto();
		newEspecieTipoAlimentacion.setIdEspecie(1002L);
		newEspecieTipoAlimentacion.setIdTipoAlimentacion(1002L);
		try {
			ResponseEspecieTipoAlimentacionDto response = especieTipoAlimentacionManagementService.create(newEspecieTipoAlimentacion);
			assertEquals(1002, response.getIdEspecie().longValue());
			assertEquals(1002, response.getIdTipoAlimentacion().longValue());
		} catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("Se esperaba que el elemento existiera");
		}
		
	}
	
	@Test
	@DisplayName("Actualiza una especie tipo alimentacion")
	public void testUpdate() {
		
		RequestUpdateEspecieTipoAlimentacionDto updateEspecieTipoAlimentacion = new RequestUpdateEspecieTipoAlimentacionDto();
		updateEspecieTipoAlimentacion.setId(1001L);
		updateEspecieTipoAlimentacion.setIdEspecie(1002L);
		updateEspecieTipoAlimentacion.setIdTipoAlimentacion(1001L);
	
		try {
			ResponseEspecieTipoAlimentacionDto response = especieTipoAlimentacionManagementService.update(updateEspecieTipoAlimentacion);
		
			assertNotNull(response);
			assertEquals(1002, response.getIdEspecie().longValue());
			assertEquals(1001, response.getIdTipoAlimentacion().longValue());
			assertEquals(1001, response.getId().longValue());
		} catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("Se esperaba que el elemento existiera");
		}
		
	}
	
	@Test
	@DisplayName("Eliminar una especie tipo alimentacion que existe")
	public void testDelete() {
		RequestDeleteEspecieTipoAlimentacionDto especieTipoAlimentacion = new RequestDeleteEspecieTipoAlimentacionDto();
		especieTipoAlimentacion.setId(1001L);
		especieTipoAlimentacion.setConfirmacion(true);
		try {
			especieTipoAlimentacionManagementService.delete(especieTipoAlimentacion);
		} catch (DinosaurioElementNotFoundException | NotConfirmDeleteDinosaurio e) {
			// TODO Auto-generated catch block
			fail("Se esperaba que el elemento existiera");
		}
	}
	
	@Test
	@DisplayName("No confirmar el borrado de una especie tipo alimentacion ")
	public void testDeleteNotConfirm() {
		RequestDeleteEspecieTipoAlimentacionDto especieTipoAlimentacion = new RequestDeleteEspecieTipoAlimentacionDto();
		especieTipoAlimentacion.setId(1001L);
		especieTipoAlimentacion.setConfirmacion(false);
		try {
			especieTipoAlimentacionManagementService.delete(especieTipoAlimentacion);
			fail("no se ha confirmado el borrado de la especie tipo alimentación");
		} catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("Se esperaba que el elemento existiera");
		} catch (NotConfirmDeleteDinosaurio e) {
			// TODO Auto-generated catch block
			
		}
	}

}
