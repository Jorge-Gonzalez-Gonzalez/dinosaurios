package es.cursojee.jurassicpark.services;

import java.util.List;

import es.cursojee.jurassicpark.controller.dto.tipoAlimentacion.RequestCreateTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.tipoAlimentacion.RequestDeleteTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.tipoAlimentacion.RequestUpdateTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.tipoAlimentacion.ResponseTipoAlimentacionDto;
import es.cursojee.jurassicpark.exception.DinosaurioElementNotFoundException;
import es.cursojee.jurassicpark.exception.NotConfirmDeleteDinosaurio;
import es.cursojee.jurassicpark.model.TipoAlimentacion;

public interface TipoAlimentacionManagementService {
	
	static final String BEAN_NAME ="tipoAlimentacionManagementService";
	
	List<ResponseTipoAlimentacionDto>findAll();
	ResponseTipoAlimentacionDto findTipoAlimentacionById(Long id) throws DinosaurioElementNotFoundException;
	ResponseTipoAlimentacionDto create(RequestCreateTipoAlimentacionDto requestCreateTipoAlimentacionDto);
	ResponseTipoAlimentacionDto update(RequestUpdateTipoAlimentacionDto requestUpdateTipoAlimentacionDto) throws DinosaurioElementNotFoundException;
	void delete(RequestDeleteTipoAlimentacionDto requestDeleteTipoAlimentacionDto) throws NotConfirmDeleteDinosaurio, DinosaurioElementNotFoundException;
	TipoAlimentacion findById(Long id) throws DinosaurioElementNotFoundException;
}
