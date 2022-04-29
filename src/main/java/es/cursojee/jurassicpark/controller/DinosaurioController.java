package es.cursojee.jurassicpark.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cursojee.jurassicpark.controller.dto.dinosaurio.RequestCreateDinosaurioDto;
import es.cursojee.jurassicpark.controller.dto.dinosaurio.RequestDeleteDinosaurioDto;
import es.cursojee.jurassicpark.controller.dto.dinosaurio.RequestUpdateDinosaurioDto;
import es.cursojee.jurassicpark.controller.dto.dinosaurio.ResponseDinosaurioDto;
import es.cursojee.jurassicpark.exception.CompartirRecintoException;
import es.cursojee.jurassicpark.exception.DinosaurioElementNotFoundException;
import es.cursojee.jurassicpark.exception.IntegratedForeignKeyException;
import es.cursojee.jurassicpark.exception.NotConfirmDeleteDinosaurio;
import es.cursojee.jurassicpark.exception.RecintoNotFoundException;
import es.cursojee.jurassicpark.exception.SobrepasadoNumeroDinosauriosEnRecintoException;
import es.cursojee.jurassicpark.services.DinosaurioManagementService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path = "/api/dinosaurio")
public class DinosaurioController {
	@Autowired
	private DinosaurioManagementService dinosaurioManagementService;
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResponseDinosaurioDto>> findAll(){
		List<ResponseDinosaurioDto>listaFamilias = dinosaurioManagementService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(listaFamilias);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDinosaurioDto> findById(@NotNull @PathVariable Long id) throws DinosaurioElementNotFoundException {
		ResponseDinosaurioDto response = dinosaurioManagementService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDinosaurioDto>  create(@Validated @RequestBody RequestCreateDinosaurioDto requestCreateDinosaurioDto) throws CompartirRecintoException, RecintoNotFoundException, SobrepasadoNumeroDinosauriosEnRecintoException, DinosaurioElementNotFoundException{
		ResponseDinosaurioDto response = dinosaurioManagementService.create(requestCreateDinosaurioDto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDinosaurioDto>  update(@Validated @RequestBody RequestUpdateDinosaurioDto requestUpdateDinosaurioDto) throws DinosaurioElementNotFoundException, RecintoNotFoundException{
		ResponseDinosaurioDto response = dinosaurioManagementService.update(requestUpdateDinosaurioDto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping(path="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@NotNull @PathVariable Long id, @Validated @RequestBody RequestDeleteDinosaurioDto requesteDeleteDinosaurioDto) throws NotConfirmDeleteDinosaurio, DinosaurioElementNotFoundException, IntegratedForeignKeyException{
		dinosaurioManagementService.delete(requesteDeleteDinosaurioDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
