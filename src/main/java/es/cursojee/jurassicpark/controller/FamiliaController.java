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

import es.cursojee.jurassicpark.controller.dto.familia.RequestCreateFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.RequestDeleteFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.RequestUpdateFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.ResponseFamiliaDto;
import es.cursojee.jurassicpark.exception.DinosaurioElementNotFoundException;
import es.cursojee.jurassicpark.exception.IntegratedForeignKeyException;
import es.cursojee.jurassicpark.exception.NotConfirmDeleteDinosaurio;
import es.cursojee.jurassicpark.services.FamiliaManagementService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path = "/api/familia")
public class FamiliaController {

	@Autowired
	private FamiliaManagementService familiaManagementService;
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResponseFamiliaDto>> findAll(){
		List<ResponseFamiliaDto>listaFamilias = familiaManagementService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(listaFamilias);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseFamiliaDto> findById(@NotNull @PathVariable Long id) throws DinosaurioElementNotFoundException {
		ResponseFamiliaDto response = familiaManagementService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseFamiliaDto>  create(@Validated @RequestBody RequestCreateFamiliaDto requestCreateFamiliaDto){
		ResponseFamiliaDto response = familiaManagementService.create(requestCreateFamiliaDto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseFamiliaDto>  update(@Validated @RequestBody RequestUpdateFamiliaDto requestUpdateFamiliaDto) throws DinosaurioElementNotFoundException{
		ResponseFamiliaDto response = familiaManagementService.update(requestUpdateFamiliaDto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping(path="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@NotNull @PathVariable Long id, @Validated @RequestBody RequestDeleteFamiliaDto requesteDeleteFamiliaDto) throws NotConfirmDeleteDinosaurio, DinosaurioElementNotFoundException, IntegratedForeignKeyException{
		familiaManagementService.delete(requesteDeleteFamiliaDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
