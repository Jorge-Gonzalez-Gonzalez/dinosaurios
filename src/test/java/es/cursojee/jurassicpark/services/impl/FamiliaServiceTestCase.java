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
import es.cursojee.jurassicpark.controller.dto.familia.RequestCreateFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.RequestDeleteFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.RequestUpdateFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.ResponseFamiliaDto;
import es.cursojee.jurassicpark.exception.DinosaurioElementNotFoundException;
import es.cursojee.jurassicpark.exception.IntegratedForeignKeyException;
import es.cursojee.jurassicpark.exception.NotConfirmDeleteDinosaurio;
import es.cursojee.jurassicpark.services.FamiliaManagementService;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@TestPropertySource(locations = "classpath:application-test.yml")
public class FamiliaServiceTestCase extends AbstractServiceTestCase  {
	
	@Autowired
	private FamiliaManagementService familiaService;
	
	@Test
	@DisplayName("Obtener todas las familias de dinosaurio existente")
	public void testFindAll() {
		List<ResponseFamiliaDto> listFamilia = familiaService.findAll();
		assertNotNull(listFamilia);
		assertEquals(5,listFamilia.size());
	}
	
	@Test
	@DisplayName("Obtener una familia existente")
	public void testFindById()  {
		ResponseFamiliaDto buscarFamilia = null;
		try {
			buscarFamilia = familiaService.findById(1001L);
			assertEquals("Saurópodos",buscarFamilia.getNombre());
			assertEquals(1001L, buscarFamilia.getId().longValue());
		} catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("Se esperaba encontrar una familia");
		}
		
	}
	
	@Test
	@DisplayName("Obtener una familia no existente")
	public void testFamiliaNoExistente() {
		try {
			familiaService.findById(0L);
			fail("Se esperaba que el elemento no existiera");
			
		} catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
		}
	}
	
	@Test
	@DisplayName("Crea una familia")
	public void testInsertFamilia() {
		RequestCreateFamiliaDto familia = new RequestCreateFamiliaDto();
		familia.setNombre("FamiliaCreada");

		ResponseFamiliaDto familiaCreada = familiaService.create(familia);
		
		assertNotNull(familiaCreada);
		assertEquals("FamiliaCreada", familiaCreada.getNombre());
		assertEquals(1006, familiaCreada.getId().longValue());
	}
	
	@Test
	@DisplayName("Actualizar una familia")
	public void testUpdate(){
		
		try {
			RequestUpdateFamiliaDto updateFamilia = new RequestUpdateFamiliaDto();
			updateFamilia.setId(1001L);
			updateFamilia.setNombre("FamiliaModificada1");		
			ResponseFamiliaDto response = familiaService.update(updateFamilia);
			assertNotNull(response);
			assertEquals("FamiliaModificada1",response.getNombre());
		} catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("No se a podido modificar la familia");
		}
		
	}
	
	@Test
	@DisplayName("Eliminar una familia que no tiene especies asociadas")
	public void testDelete() {
		try {
			RequestDeleteFamiliaDto borrar = new RequestDeleteFamiliaDto();
			borrar.setId(1005L);
			borrar.setConfirmacion(true);
			familiaService.delete(borrar);
		}		
		catch (NotConfirmDeleteDinosaurio e) {
				// TODO Auto-generated catch block
				fail("No se ha confirmado el borrado de la familia");
		} catch (IntegratedForeignKeyException e) {
			// TODO Auto-generated catch block
			fail("Esta familia esta asociada a una especie o un conjunto de especies");
			
		} catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("Se esperaba que la familia existiese");
		}
	}

	@Test
	@DisplayName("Comprobar que no se puede eliminar una familia que tiene especies asociadas")
	public void testNotDeleteFamilia() {
		try {
			RequestDeleteFamiliaDto borrar = new RequestDeleteFamiliaDto();
			borrar.setId(1001L);
			borrar.setConfirmacion(true);
			familiaService.delete(borrar);
			fail("Esta familia esta asociada a una especie o un conjunto de especies");
		}		
		catch (NotConfirmDeleteDinosaurio e) {
				// TODO Auto-generated catch block
				fail("No se ha confirmado el borrado de la familia");
		} catch (IntegratedForeignKeyException e) {
			// TODO Auto-generated catch block
			
			
		} catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("Se esperaba que la familia existiese");
		}
	}
	
	@Test
	@DisplayName("No se ha confirmado el borrado de la familia")
	public void testDeleteNotConfirm() {
		try {
			RequestDeleteFamiliaDto borrar = new RequestDeleteFamiliaDto();
			borrar.setId(1005L);
			borrar.setConfirmacion(false);
			familiaService.delete(borrar);
			fail("No se ha confirmado el borrado de la familia");
		}		
		catch (NotConfirmDeleteDinosaurio e) {
				// TODO Auto-generated catch block
				
		} catch (IntegratedForeignKeyException e) {
			// TODO Auto-generated catch block
			fail("Esta familia esta asociada a una especie o un conjunto de especies");
			
		} catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("Se esperaba que la familia existiese");
		}
	}

}
