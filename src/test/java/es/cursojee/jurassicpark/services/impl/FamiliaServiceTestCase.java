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
import es.cursojee.jurassicpark.exception.NotConfirmDeleteDinosaurio;
import es.cursojee.jurassicpark.model.Familia;
import es.cursojee.jurassicpark.services.FamiliaService;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@TestPropertySource(locations = "classpath:application-test.yml")
public class FamiliaServiceTestCase extends AbstractServiceTestCase  {
	
	@Autowired
	private FamiliaService familiaService;
	
	@Test
	@DisplayName("Obtener todas las familias de dinosaurio existente")
	public void testFindAll() {
		List<ResponseFamiliaDto> listFamilia = familiaService.findAll();
		assertNotNull(listFamilia);
		assertEquals(4,listFamilia.size());
	}
	
	@Test
	@DisplayName("Obtener una familia existente")
	public void testFindById()  {
		ResponseFamiliaDto buscarFamilia = null;
		try {
			buscarFamilia = familiaService.findFamiliaById(1001L);
			assertEquals("Familia1",buscarFamilia.getNombre());
		} catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("Se esperaba buscar una familia");
		}
		
	}
	
	@Test
	@DisplayName("Obtener una familia no existente")
	public void testFamiliaNoExistente() {
		try {
			familiaService.findFamiliaById(0L);
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
		//assertEquals(1, familiaCreada.getId().intValue());
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
	@DisplayName("Eliminar una familia")
	public void testDelete() {
		try {
			Familia borrarFamilia = familiaService.findById(1001L);
			RequestDeleteFamiliaDto borrar = new RequestDeleteFamiliaDto();
			borrar.setId(borrarFamilia.getId());
			borrar.setConfirmacion(true);
			familiaService.delete(borrar);
		
			} catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("No se ha podido borrar la familia");
		} 
		
		catch (NotConfirmDeleteDinosaurio e) {
				// TODO Auto-generated catch block
				fail("No se ha confirmado el borrado de la familia");
		}
	}

}
