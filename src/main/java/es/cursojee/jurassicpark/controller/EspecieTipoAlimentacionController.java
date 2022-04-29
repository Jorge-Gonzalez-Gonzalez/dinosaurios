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

import es.cursojee.jurassicpark.controller.dto.especieTipoAlimentacion.RequestCreateEspecieTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.especieTipoAlimentacion.RequestDeleteEspecieTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.especieTipoAlimentacion.RequestUpdateEspecieTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.especieTipoAlimentacion.ResponseEspecieTipoAlimentacionDto;
import es.cursojee.jurassicpark.exception.DinosaurioElementNotFoundException;
import es.cursojee.jurassicpark.exception.IntegratedForeignKeyException;
import es.cursojee.jurassicpark.exception.NotConfirmDeleteDinosaurio;
import es.cursojee.jurassicpark.services.EspecieTipoAlimentacionManagementService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path = "/api/especie-tipo-alimentacion")
public class EspecieTipoAlimentacionController {
	
	@Autowired
	private EspecieTipoAlimentacionManagementService especieTipoAlimentacionManagementService;
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResponseEspecieTipoAlimentacionDto>> findAll(){
		List<ResponseEspecieTipoAlimentacionDto>listaFamilias = especieTipoAlimentacionManagementService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(listaFamilias);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEspecieTipoAlimentacionDto> findById(@NotNull @PathVariable Long id) throws DinosaurioElementNotFoundException {
		ResponseEspecieTipoAlimentacionDto response = especieTipoAlimentacionManagementService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEspecieTipoAlimentacionDto>  create(@Validated @RequestBody RequestCreateEspecieTipoAlimentacionDto requestCreateEspecieTipoAlimentacionDto) throws DinosaurioElementNotFoundException{
		ResponseEspecieTipoAlimentacionDto response =especieTipoAlimentacionManagementService.create(requestCreateEspecieTipoAlimentacionDto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEspecieTipoAlimentacionDto>  update(@Validated @RequestBody RequestUpdateEspecieTipoAlimentacionDto requestUpdateEspecieTipoAlimentacionDto) throws DinosaurioElementNotFoundException{
		ResponseEspecieTipoAlimentacionDto response = especieTipoAlimentacionManagementService.update(requestUpdateEspecieTipoAlimentacionDto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping(path="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@NotNull @PathVariable Long id, @Validated @RequestBody RequestDeleteEspecieTipoAlimentacionDto requesteDeleteEspecieTipoAlimentacionDto) throws NotConfirmDeleteDinosaurio, DinosaurioElementNotFoundException, IntegratedForeignKeyException{
		especieTipoAlimentacionManagementService.delete(requesteDeleteEspecieTipoAlimentacionDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
