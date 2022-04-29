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

import es.cursojee.jurassicpark.controller.dto.tipoAlimentacion.RequestCreateTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.tipoAlimentacion.RequestDeleteTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.tipoAlimentacion.RequestUpdateTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.tipoAlimentacion.ResponseTipoAlimentacionDto;
import es.cursojee.jurassicpark.exception.DinosaurioElementNotFoundException;
import es.cursojee.jurassicpark.exception.IntegratedForeignKeyException;
import es.cursojee.jurassicpark.exception.NotConfirmDeleteDinosaurio;
import es.cursojee.jurassicpark.services.TipoAlimentacionManagementService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path = "/api/tipo-alimentacion")
public class TipoAlimentacionController {
	
	@Autowired
	private TipoAlimentacionManagementService tipoAlimentacionManagementService;
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResponseTipoAlimentacionDto>> findAll(){
		List<ResponseTipoAlimentacionDto>listaTipoAlimentaciones = tipoAlimentacionManagementService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(listaTipoAlimentaciones);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseTipoAlimentacionDto> findById(@NotNull @PathVariable Long id) throws DinosaurioElementNotFoundException{
		ResponseTipoAlimentacionDto response = tipoAlimentacionManagementService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseTipoAlimentacionDto>  create(@Validated @RequestBody RequestCreateTipoAlimentacionDto requestCreateTipoAlimentacionDto){
		ResponseTipoAlimentacionDto response = tipoAlimentacionManagementService.create(requestCreateTipoAlimentacionDto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseTipoAlimentacionDto>  update(@Validated @RequestBody RequestUpdateTipoAlimentacionDto requestUpdateTipoAlimentacionDto) throws DinosaurioElementNotFoundException{
		ResponseTipoAlimentacionDto response = tipoAlimentacionManagementService.update(requestUpdateTipoAlimentacionDto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping(path="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@NotNull @PathVariable Long id, @Validated @RequestBody RequestDeleteTipoAlimentacionDto requesteDeleteTipoAlimentacionDto) throws NotConfirmDeleteDinosaurio, DinosaurioElementNotFoundException, IntegratedForeignKeyException{
		tipoAlimentacionManagementService.delete(requesteDeleteTipoAlimentacionDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}


}
