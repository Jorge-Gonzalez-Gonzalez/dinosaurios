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
import es.cursojee.jurassicpark.controller.dto.tipoAlimentacion.RequestCreateTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.tipoAlimentacion.RequestDeleteTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.tipoAlimentacion.RequestUpdateTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.tipoAlimentacion.ResponseTipoAlimentacionDto;
import es.cursojee.jurassicpark.exception.DinosaurioElementNotFoundException;
import es.cursojee.jurassicpark.exception.IntegratedForeignKeyException;
import es.cursojee.jurassicpark.exception.NotConfirmDeleteDinosaurio;
import es.cursojee.jurassicpark.services.TipoAlimentacionManagementService;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@TestPropertySource(locations = "classpath:application-test.yml")
public class TipoAlimentacionServiceTestCase extends AbstractServiceTestCase{
	
	@Autowired
	private TipoAlimentacionManagementService tipoAlimentacionService;
	
	@Test
	@DisplayName("Obtener todos los tipos de alimentaciones de dinosaurio existente")
	public void testFindAll() {
		List<ResponseTipoAlimentacionDto> listaAlimentacion = tipoAlimentacionService.findAll();
		assertEquals(3,listaAlimentacion.size());
	}
	
	@Test
	@DisplayName("Obtener un tipo de alimentaci??n existente")
	public void testTipoAlimentacionExistente() {
		try {
			ResponseTipoAlimentacionDto respuesta = tipoAlimentacionService.findById(1002L);
			assertNotNull(respuesta);
			assertEquals(1002, respuesta.getId().longValue());	
			assertEquals("Carn??voro", respuesta.getDescripcion());
			
			
		} catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("Se esperaba que el elemento existiera");
		}
	}
	
	@Test
	@DisplayName("Obtener un tipo de alimentaci??n no existente")
	public void testFindByIdNotExist() {
		
		try {
			tipoAlimentacionService.findById(0L);
			fail("Se esperaba que el elemento no existiera");
		} catch (DinosaurioElementNotFoundException e) {

		}
	
	}
	
	@Test
	@DisplayName("Crea un tipo de alimentaci??n")
	public void testCreate() {
		RequestCreateTipoAlimentacionDto tipoAlimentacion = new RequestCreateTipoAlimentacionDto();
		tipoAlimentacion.setDescripcion("TipoAlimentacionCreado");
		ResponseTipoAlimentacionDto response = tipoAlimentacionService.create(tipoAlimentacion);
		assertNotNull(response);
		assertEquals("TipoAlimentacionCreado",response.getDescripcion());
		assertEquals(1004, response.getId().longValue());
	}
	
	@Test
	@DisplayName("Actualiza un tipo de alimentaci??n")
	public void testUpdate() {
		RequestUpdateTipoAlimentacionDto updateTipoAlimentacion = new RequestUpdateTipoAlimentacionDto();
		updateTipoAlimentacion.setId(1001L);
		updateTipoAlimentacion.setDescripcion("NuevaAlimentacionActualizada");
		
		try {
			ResponseTipoAlimentacionDto response = tipoAlimentacionService.update(updateTipoAlimentacion);
			assertNotNull(response);
			assertEquals("NuevaAlimentacionActualizada",response.getDescripcion());
		} catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("No se ha actualizado la familia");
		}
		
		
	}
	
	@Test
	@DisplayName("Comprobar que se puede eliminar un tipo de alimentaci??n que no tiene especies asociadas")
	public void testDelete() {
		RequestDeleteTipoAlimentacionDto deleteAlimentacion = new RequestDeleteTipoAlimentacionDto();
		deleteAlimentacion.setId(1003L);
		deleteAlimentacion.setConfirmacion(true);
		
		try {
			tipoAlimentacionService.delete(deleteAlimentacion);
		} catch (NotConfirmDeleteDinosaurio | DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("no se ha borrado el tipo de alimentaci??n");
		} catch (IntegratedForeignKeyException e) {
			// TODO Auto-generated catch block
			fail("no se ha borrado el tipo de alimentaci??n porque tiene especies asociadas");
		}
	}
	
	@Test
	@DisplayName("Comprobar que no se puede eliminar un tipo de alimentaci??n que tiene especies asociadas")
	public void testNotDeleteTipoAlimentacion() {
		RequestDeleteTipoAlimentacionDto deleteAlimentacion = new RequestDeleteTipoAlimentacionDto();
		deleteAlimentacion.setId(1001L);
		deleteAlimentacion.setConfirmacion(true);
		
		try {
			tipoAlimentacionService.delete(deleteAlimentacion);
			fail("Este tipo de alimentaci??n tiene especies asociadas");
		} catch (NotConfirmDeleteDinosaurio | DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("no se ha borrado el tipo de alimentaci??n");
		} catch (IntegratedForeignKeyException e) {
			// TODO Auto-generated catch block
			
		}
	}
	
	@Test
	@DisplayName("No se ha confirmado el borrado de tipo alimentacion")
	public void testDeleteNotConfirm() {
		RequestDeleteTipoAlimentacionDto deleteAlimentacion = new RequestDeleteTipoAlimentacionDto();
		deleteAlimentacion.setId(1003L);
		deleteAlimentacion.setConfirmacion(false);
		
		try {
			tipoAlimentacionService.delete(deleteAlimentacion);
		} catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("no se ha borrado el tipo de alimentaci??n");
		} catch (IntegratedForeignKeyException e) {
			// TODO Auto-generated catch block
			fail("no se ha borrado el tipo de alimentaci??n porque tiene especies asociadas");
		} catch (NotConfirmDeleteDinosaurio e) {
			// TODO Auto-generated catch block
			
		}
	}

}
