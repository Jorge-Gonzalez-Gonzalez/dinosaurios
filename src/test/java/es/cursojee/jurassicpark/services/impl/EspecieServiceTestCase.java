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
import es.cursojee.jurassicpark.controller.dto.especie.RequestCreateEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.RequestDeleteEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.RequestUpdateEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.ResponseEspecieDto;
import es.cursojee.jurassicpark.exception.DinosaurioElementNotFoundException;
import es.cursojee.jurassicpark.exception.IntegratedForeignKeyException;
import es.cursojee.jurassicpark.exception.NotConfirmDeleteDinosaurio;
import es.cursojee.jurassicpark.model.tipoPeligrosidad.CodigoTipoPeligrosidad;
import es.cursojee.jurassicpark.services.EspecieManagementService;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@TestPropertySource(locations = "classpath:application-test.yml")
public class EspecieServiceTestCase extends AbstractServiceTestCase{
	
	@Autowired
	private EspecieManagementService especieManagementService;
	
	@Test
	@DisplayName("Obtener todas las especies de dinosaurio existentes")
	public void testFindAll() {
		List<ResponseEspecieDto> listaEspecie = especieManagementService.findAll();
		assertNotNull(listaEspecie);
		assertEquals(6,listaEspecie.size());
	}
	
	@Test
	@DisplayName("Obtener una especie existente")
	public void testFindByIdExist() {
		try {
			ResponseEspecieDto buscarEspecie = especieManagementService.findById(1001L);
			assertNotNull(buscarEspecie);
			assertEquals("Diplodocus",buscarEspecie.getNombre());
			assertEquals(CodigoTipoPeligrosidad.BAJA,buscarEspecie.getCodigoTipoPeligrosidad());
			assertEquals(50,buscarEspecie.getLongitud().intValue());
			assertEquals(1001, buscarEspecie.getIdFamilia().longValue());		
		
		} catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("Se esperaba que el elemento existiera");
		}
	}
	
	@Test
	@DisplayName("Obtener una especie no existente")
	public void testFindByIdNotExist() {

		try {
			ResponseEspecieDto response = especieManagementService.findById(10L);
			fail("Se esperaba que el elemento no existiera");
		} catch (DinosaurioElementNotFoundException e) {
			
		}

	}
	
	@Test
	@DisplayName("Crea una especie")
	public void testCreate() {
		RequestCreateEspecieDto newEspecie = new RequestCreateEspecieDto();
		
		newEspecie.setCodigoTipoPeligrosidad(CodigoTipoPeligrosidad.ALTA);
		newEspecie.setIdFamilia(1001L);
		newEspecie.setLongitud(100);
		newEspecie.setNombre("Triceratos");
		
		try {
			ResponseEspecieDto response = especieManagementService.create(newEspecie);
			
			assertNotNull(response);
			assertEquals("Triceratos",response.getNombre());
			assertEquals(1001,response.getIdFamilia().longValue());
			assertEquals(CodigoTipoPeligrosidad.ALTA,response.getCodigoTipoPeligrosidad());
			assertEquals(100,response.getLongitud().intValue());
			
		} catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("Se esperaba que el elemento existiera");
		}
	}
	
	@Test
	@DisplayName("Actualiza una especie")
	public void testUpdate() {
		
		RequestUpdateEspecieDto newEspecie = new RequestUpdateEspecieDto();
		newEspecie.setId(1004L);
		newEspecie.setIdFamilia(1002L);
		newEspecie.setCodigoTipoPeligrosidad(CodigoTipoPeligrosidad.ALTA);
		newEspecie.setLongitud(100);
		newEspecie.setNombre("Triceratos");
		
		try {
			ResponseEspecieDto response = especieManagementService.update(newEspecie);
			assertNotNull(response);
			assertEquals("Triceratos",response.getNombre());
			assertEquals(1002,response.getIdFamilia().longValue());
			assertEquals(CodigoTipoPeligrosidad.ALTA,response.getCodigoTipoPeligrosidad());
			assertEquals(100,response.getLongitud().intValue());
			assertEquals(1004,response.getId().longValue());
			
		} catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("Se esperaba que el elemento existiera");
		}
	}
	  
	@Test
	@DisplayName("Comprobar que se puede eliminar una especie que no tiene alimentacion asociada")
	public void testDelete() {
		RequestDeleteEspecieDto especie = new RequestDeleteEspecieDto();
		especie.setId(1006L);
		especie.setConfirmacion(true);
		try {
			especieManagementService.delete(especie);
		} catch (DinosaurioElementNotFoundException | NotConfirmDeleteDinosaurio e) {
			// TODO Auto-generated catch block
			fail("Se esperaba que el elemento existiera");
		} catch (IntegratedForeignKeyException e) {
			// TODO Auto-generated catch block
			fail("Esta especie tiene un tipo de alimentación asociadas");
		}
	}
	
	@Test
	@DisplayName("Comprobar que no se puede eliminar una especie que tiene alimentacion asociada")
	public void testNotDeleteEspecie() {
		RequestDeleteEspecieDto especie = new RequestDeleteEspecieDto();
		especie.setId(1001L);
		especie.setConfirmacion(true);
		try {
			especieManagementService.delete(especie);
			fail("Esta especie tiene un tipo de alimentación asociadas");
		} catch (DinosaurioElementNotFoundException | NotConfirmDeleteDinosaurio e) {
			// TODO Auto-generated catch block
			fail("Se esperaba que el elemento existiera");
		} catch (IntegratedForeignKeyException e) {
			// TODO Auto-generated catch block
			
		}
	}
	
	@Test
	@DisplayName("No confirmar la eliminación de la especie")
	public void testDeleteNotConfirm() {
		RequestDeleteEspecieDto especie = new RequestDeleteEspecieDto();
		especie.setId(1001L);
		especie.setConfirmacion(false);
		
		try {
			especieManagementService.delete(especie);
			fail("No se ha confirmado el borrado de la especie");
		} catch (DinosaurioElementNotFoundException e) {
			// TODO Auto-generated catch block
			fail("Se esperaba que la especie existiera");
		} catch (IntegratedForeignKeyException e) {
			// TODO Auto-generated catch block
			fail("Esta especie tiene un tipo de alimentación asociadas");
		} catch (NotConfirmDeleteDinosaurio e) {
			// TODO Auto-generated catch block
			
		}
	}
}

