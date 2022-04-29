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

import es.cursojee.jurassicpark.controller.dto.recinto.RequestCreateRecintoDto;
import es.cursojee.jurassicpark.controller.dto.recinto.RequestDeleteRecintoDto;
import es.cursojee.jurassicpark.controller.dto.recinto.RequestUpdateRecintoDto;
import es.cursojee.jurassicpark.controller.dto.recinto.ResponseRecintoDto;
import es.cursojee.jurassicpark.exception.IntegratedForeignKeyException;
import es.cursojee.jurassicpark.exception.NotConfirmRecintoDelete;
import es.cursojee.jurassicpark.exception.RecintoNotFoundException;
import es.cursojee.jurassicpark.services.RecintoManagementService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path = "/api/recinto")
public class RecintoController {
	
	@Autowired
	private RecintoManagementService recintoManagementService;
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResponseRecintoDto>> findAll(){
		List<ResponseRecintoDto>listaRecintos = recintoManagementService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(listaRecintos);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseRecintoDto> findById(@NotNull @PathVariable Long id) throws RecintoNotFoundException{
		ResponseRecintoDto response = recintoManagementService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseRecintoDto>  create(@Validated @RequestBody RequestCreateRecintoDto requestCreateRecintoDto){
		ResponseRecintoDto response = recintoManagementService.create(requestCreateRecintoDto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseRecintoDto>  update(@Validated @RequestBody RequestUpdateRecintoDto requestUpdateRecintoDto) throws RecintoNotFoundException {
		ResponseRecintoDto response = recintoManagementService.update(requestUpdateRecintoDto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping(path="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@NotNull @PathVariable Long id, @Validated @RequestBody RequestDeleteRecintoDto requesteDeleteRecintoDto) throws IntegratedForeignKeyException, NotConfirmRecintoDelete, RecintoNotFoundException{
		recintoManagementService.delete(requesteDeleteRecintoDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
