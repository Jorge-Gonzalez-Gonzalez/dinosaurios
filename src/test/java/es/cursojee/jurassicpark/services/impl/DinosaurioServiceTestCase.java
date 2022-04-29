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
import es.cursojee.jurassicpark.controller.dto.dinosaurio.RequestCreateDinosaurioDto;
import es.cursojee.jurassicpark.controller.dto.dinosaurio.RequestDeleteDinosaurioDto;
import es.cursojee.jurassicpark.controller.dto.dinosaurio.RequestUpdateDinosaurioDto;
import es.cursojee.jurassicpark.controller.dto.dinosaurio.ResponseDinosaurioDto;
import es.cursojee.jurassicpark.exception.CompartirRecintoException;
import es.cursojee.jurassicpark.exception.DinosaurioElementNotFoundException;
import es.cursojee.jurassicpark.exception.NotConfirmDeleteDinosaurio;
import es.cursojee.jurassicpark.exception.RecintoNotFoundException;
import es.cursojee.jurassicpark.exception.SobrepasadoNumeroDinosauriosEnRecintoException;
import es.cursojee.jurassicpark.model.sexo.Sexo;
import es.cursojee.jurassicpark.services.DinosaurioManagementService;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@TestPropertySource(locations = "classpath:application-test.yml")
public class DinosaurioServiceTestCase extends AbstractServiceTestCase{

	@Autowired
	private DinosaurioManagementService dinosaurioManagementService;
	
	@Test
	@DisplayName("Obtener todos los dinosaurios existentes")
	public void testFindAll() {
		List<ResponseDinosaurioDto> listaDinosaurios = dinosaurioManagementService.findAll();
		assertNotNull(listaDinosaurios);
		assertEquals(6,listaDinosaurios.size());
	}
	
	@Test
	@DisplayName("Obtener un dinosaurio existente")
	public void testFindByIdExist() {
		try {
			ResponseDinosaurioDto buscarDinosaurio = dinosaurioManagementService.findById(1001L);
			assertNotNull(buscarDinosaurio);
			assertEquals("Jacky",buscarDinosaurio.getNombre());
			assertEquals(Sexo.MACHO,buscarDinosaurio.getSexo());
			assertEquals(1004,buscarDinosaurio.getIdEspecie().longValue());
			assertEquals(1001, buscarDinosaurio.getIdRecinto().longValue());		
		
		} catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("Se esperaba que el dinosaurio existiera");
		}
	}
	
	@Test
	@DisplayName("Obtener un dinosaurio no existente")
	public void testFindByIdNotExist() {

		try {
			ResponseDinosaurioDto response = dinosaurioManagementService.findById(10L);
			fail("Se esperaba que el dinosaurio no existiera");
		} catch (DinosaurioElementNotFoundException e) {
			
		}

	}
	
	@Test
	@DisplayName("Crea un dinosaurio")
	public void testCreate() {
		RequestCreateDinosaurioDto newDinosaurio = new RequestCreateDinosaurioDto();
		
		newDinosaurio.setNombre("Tiranosaurus Rex");
		newDinosaurio.setSexo(Sexo.HEMBRA);
		newDinosaurio.setIdRecinto(1002L);
		newDinosaurio.setIdEspecie(1003L);
		
		try {
			ResponseDinosaurioDto response = dinosaurioManagementService.create(newDinosaurio);
			
			assertNotNull(newDinosaurio);
			assertEquals("Tiranosaurus Rex",newDinosaurio.getNombre());
			assertEquals(Sexo.HEMBRA,newDinosaurio.getSexo());
			assertEquals(1003L,newDinosaurio.getIdEspecie().longValue());
			assertEquals(1002L, newDinosaurio.getIdRecinto().longValue());
		}catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("Se esperaba que el dinosaurio existiera");
		} catch (CompartirRecintoException e) {
			// TODO Auto-generated catch block
			fail("No se pueden meter herbívoros y carnívoros en el menso recinto");
		} catch (RecintoNotFoundException e) {
			// TODO Auto-generated catch block
			fail("No existe ese recinto");
		} catch (SobrepasadoNumeroDinosauriosEnRecintoException e) {
			// TODO Auto-generated catch block
			fail("Ha superado el máximo de 4 dinosaurios por recinto");
		}
	}
	
	@Test
	@DisplayName("Modificar un dinosaurio")
	public void testUpdate() {
		RequestUpdateDinosaurioDto newDinosaurio = new RequestUpdateDinosaurioDto();
		newDinosaurio.setId(1001L);
		newDinosaurio.setNombre("Lucky");
		newDinosaurio.setSexo(Sexo.HEMBRA);
		newDinosaurio.setIdRecinto(1001L);
		newDinosaurio.setIdEspecie(1004L);
		
		try {
			ResponseDinosaurioDto response = dinosaurioManagementService.update(newDinosaurio);
			
			assertNotNull(newDinosaurio);
			assertEquals("Lucky",newDinosaurio.getNombre());
			assertEquals(Sexo.HEMBRA,newDinosaurio.getSexo());
			assertEquals(1004L,newDinosaurio.getIdEspecie().longValue());
			assertEquals(1001L, newDinosaurio.getIdRecinto().longValue());
		}catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("Se esperaba que el dinosaurio existiera");
		} catch (RecintoNotFoundException e) {
			// TODO Auto-generated catch block
			fail("NO existe ese recinto");
		}
	}
	
	@Test
	@DisplayName("Verificar que no se puede meter más de 4 dinosaurios en un recinto")
	public void testVerificarNumDinosauriosPorRecinto() {
		RequestCreateDinosaurioDto newDinosaurio = new RequestCreateDinosaurioDto();
		
		newDinosaurio.setNombre("Diplodocus");
		newDinosaurio.setSexo(Sexo.HEMBRA);
		newDinosaurio.setIdRecinto(1001L);
		newDinosaurio.setIdEspecie(1001L);
		
		try {
			ResponseDinosaurioDto response = dinosaurioManagementService.create(newDinosaurio);
			fail("Ha superado el máximo de 4 dinosaurios por recinto");
			
		}catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("Se esperaba que el dinosaurio existiera");
		} catch (CompartirRecintoException e) {
			// TODO Auto-generated catch block
			fail("No se pueden meter herbívoros y carnívoros en el menso recinto");
		} catch (RecintoNotFoundException e) {
			// TODO Auto-generated catch block
			fail("No existe ese recinto");
		} catch (SobrepasadoNumeroDinosauriosEnRecintoException e) {
			// TODO Auto-generated catch block
			
		}
	}
	
	@Test
	@DisplayName("Verificar que no se puede meter herbívoros y carnívoros en el mismo recinto")
	public void testHerbivorosCarnivoros() {
		RequestCreateDinosaurioDto newDinosaurio = new RequestCreateDinosaurioDto();
		
		newDinosaurio.setNombre("Diplodocus");
		newDinosaurio.setSexo(Sexo.HEMBRA);
		newDinosaurio.setIdRecinto(1002L);
		newDinosaurio.setIdEspecie(1001L);
		
		
		try {		
			
		ResponseDinosaurioDto response = dinosaurioManagementService.create(newDinosaurio);
		fail("No se pueden meter herbívoros y carnívoros en el menso recinto");
			
			
		}catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("Se esperaba que el dinosaurio existiera");
		} catch (CompartirRecintoException e) {
			// TODO Auto-generated catch block
			
		} catch (RecintoNotFoundException e) {
			// TODO Auto-generated catch block
			fail("No existe ese recinto");
		} catch (SobrepasadoNumeroDinosauriosEnRecintoException e) {
			// TODO Auto-generated catch block
			fail("Ha superado el máximo de 4 dinosaurios por recinto");
			
		}
	}
	
	@Test
	@DisplayName("Eliminar un dinosaurio que existe")
	public void Delete() {
		RequestDeleteDinosaurioDto deleteDinosaurio = new RequestDeleteDinosaurioDto();
		
		deleteDinosaurio.setId(1001L);
		deleteDinosaurio.setConfirmacion(true);
		
		try {
			dinosaurioManagementService.delete(deleteDinosaurio);
		} catch (NotConfirmDeleteDinosaurio e) {
			// TODO Auto-generated catch block
			fail("No ha confirmado el borrado del dinosaurio");
		} catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("No existe el dinosaurio");
		}
	}
	
	@Test
	@DisplayName("No confirmar la eliminación del dinosaurio")
	public void DeleteNotConfirm() {
		RequestDeleteDinosaurioDto deleteDinosaurio = new RequestDeleteDinosaurioDto();
		
		deleteDinosaurio.setId(1001L);
		deleteDinosaurio.setConfirmacion(false);
		
		try {
			dinosaurioManagementService.delete(deleteDinosaurio);
			fail("No ha confirmado el borrado del dinosaurio");
		} catch (NotConfirmDeleteDinosaurio e) {
			// TODO Auto-generated catch block
			
		} catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("No existe el dinosaurio");
		}
	}
}
