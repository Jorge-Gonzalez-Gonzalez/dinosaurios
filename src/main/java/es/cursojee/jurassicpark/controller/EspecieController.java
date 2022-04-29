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

import es.cursojee.jurassicpark.controller.dto.especie.RequestCreateEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.RequestDeleteEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.RequestUpdateEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.ResponseEspecieDto;
import es.cursojee.jurassicpark.exception.DinosaurioElementNotFoundException;
import es.cursojee.jurassicpark.exception.IntegratedForeignKeyException;
import es.cursojee.jurassicpark.exception.NotConfirmDeleteDinosaurio;
import es.cursojee.jurassicpark.services.EspecieManagementService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path = "/api/especie")
public class EspecieController {

	@Autowired
	private EspecieManagementService especieManagementService;
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResponseEspecieDto>> findAll(){
		List<ResponseEspecieDto>listaFamilias = especieManagementService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(listaFamilias);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEspecieDto> findById(@NotNull @PathVariable Long id) throws DinosaurioElementNotFoundException {
		ResponseEspecieDto response = especieManagementService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEspecieDto>  create(@Validated @RequestBody RequestCreateEspecieDto requestCreateEspecieDto) throws DinosaurioElementNotFoundException{
		ResponseEspecieDto response = especieManagementService.create(requestCreateEspecieDto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEspecieDto>  update(@Validated @RequestBody RequestUpdateEspecieDto requestUpdateEspecieDto) throws DinosaurioElementNotFoundException{
		ResponseEspecieDto response = especieManagementService.update(requestUpdateEspecieDto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping(path="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@NotNull @PathVariable Long id, @Validated @RequestBody RequestDeleteEspecieDto requesteDeleteEspecieDto) throws NotConfirmDeleteDinosaurio, DinosaurioElementNotFoundException, IntegratedForeignKeyException{
		especieManagementService.delete(requesteDeleteEspecieDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
