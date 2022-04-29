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
import es.cursojee.jurassicpark.controller.dto.recinto.RequestCreateRecintoDto;
import es.cursojee.jurassicpark.controller.dto.recinto.RequestDeleteRecintoDto;
import es.cursojee.jurassicpark.controller.dto.recinto.RequestUpdateRecintoDto;
import es.cursojee.jurassicpark.controller.dto.recinto.ResponseRecintoDto;
import es.cursojee.jurassicpark.exception.IntegratedForeignKeyException;
import es.cursojee.jurassicpark.exception.NotConfirmRecintoDelete;
import es.cursojee.jurassicpark.exception.RecintoNotFoundException;
import es.cursojee.jurassicpark.services.RecintoManagementService;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@TestPropertySource(locations = "classpath:application-test.yml")
public class RecintoServiceTestCase extends AbstractServiceTestCase {

	@Autowired 
	private RecintoManagementService recintoManagementService;
	

	@Test
	@DisplayName("Obtener todas los recintos existente")
	public void testFindAll() {
		List<ResponseRecintoDto> listRecinto = recintoManagementService.findAll();
		assertNotNull(listRecinto);
		assertEquals(3,listRecinto.size());
	}
	
	@Test
	@DisplayName("Obtener un recinto existente")
	public void testFindById()  {
		ResponseRecintoDto buscarRecinto = null;
		try {
			buscarRecinto = recintoManagementService.findById(1001L);
			assertEquals("Recinto1",buscarRecinto.getNombre());
			assertEquals(1001, buscarRecinto.getId().longValue());
		} catch (RecintoNotFoundException e) {
			// TODO Auto-generated catch block
			fail("Se esperaba encontrar un recinto");
		}
		
	}
	
	@Test
	@DisplayName("Obtener un recinto no existente")
	public void testRecintoNoExistente() {
		try {
			recintoManagementService.findById(0L);
			fail("Se esperaba que el recinto no existiera");
			
		} catch (RecintoNotFoundException e) {
			// TODO Auto-generated catch block
			
		}
	}
	
	@Test
	@DisplayName("Crea un recinto")
	public void testCreate() {
		RequestCreateRecintoDto newRecinto = new RequestCreateRecintoDto();
		newRecinto.setNombre("RecintoCreado");
		newRecinto.setNumDinosaurios(0);
		newRecinto.setTipoRecinto("Carnívoro");
		
		ResponseRecintoDto response = recintoManagementService.create(newRecinto);
	
		assertNotNull(response);
		assertEquals("RecintoCreado", response.getNombre());
		assertEquals(1004, response.getId().longValue());
	}
	
	@Test
	@DisplayName("Actualizar un recinto")
	public void testUpdate(){
		
		try {
			RequestUpdateRecintoDto updateRecinto = new RequestUpdateRecintoDto();
			updateRecinto.setId(1001L);
			updateRecinto.setNombre("RecintoModificada1");
			updateRecinto.setNumDinosaurios(0);
			updateRecinto.setTipoRecinto("Carnívoro");
			ResponseRecintoDto response = recintoManagementService.update(updateRecinto);
			assertNotNull(response);
			assertEquals("RecintoModificada1",response.getNombre());
			assertEquals("Carnívoro",response.getTipoRecinto());
			assertEquals(1001, response.getId().longValue());
		
		} catch (RecintoNotFoundException e) {
			// TODO Auto-generated catch block
			fail("No se a podido modificar el recinto");
		}
		
	}
	
	@Test
	@DisplayName("Eliminar un recinto que no tiene dinosaurios asociadas")
	public void testDelete() {
		try {
			RequestDeleteRecintoDto borrarRecinto = new RequestDeleteRecintoDto();
			borrarRecinto.setId(1003L);
			borrarRecinto.setConfirmacion(true);
				
			recintoManagementService.delete(borrarRecinto);

		}catch (NotConfirmRecintoDelete e) {
				// TODO Auto-generated catch block
				fail("No se ha confirmado el borrado del recinto");
		} catch (IntegratedForeignKeyException e) {
			// TODO Auto-generated catch block
			fail("Este recinto está asociado con dinosaurios");
		} catch (RecintoNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Eliminar un recinto que tiene dinosaurios asociadas")
	public void testNotDeleteRecinto() {
		try {
			RequestDeleteRecintoDto borrarRecinto = new RequestDeleteRecintoDto();
			borrarRecinto.setId(1001L);
			borrarRecinto.setConfirmacion(true);
			recintoManagementService.delete(borrarRecinto);
			fail("Este recinto esta asociado con dinosaurios");
		}catch (NotConfirmRecintoDelete e) {
				// TODO Auto-generated catch block
				fail("No se ha confirmado el borrado del recinto");
		} catch (IntegratedForeignKeyException e) {
			// TODO Auto-generated catch block
			
		} catch (RecintoNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("No se ha confirmado el borrado del recinto")
	public void testDeleteNotConfirm() {
		try {
			RequestDeleteRecintoDto borrarRecinto = new RequestDeleteRecintoDto();
			borrarRecinto.setId(1003L);
			borrarRecinto.setConfirmacion(false);
				
			recintoManagementService.delete(borrarRecinto);
			fail("No se ha confirmado el borrado del recinto");

		}catch (NotConfirmRecintoDelete e) {
				// TODO Auto-generated catch block
				
		} catch (IntegratedForeignKeyException e) {
			// TODO Auto-generated catch block
			fail("Este recinto está asociado con dinosaurios");
		} catch (RecintoNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
