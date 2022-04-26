package es.cursojee.jurassicpark.services;

import java.util.List;

import es.cursojee.jurassicpark.controller.dto.dinosaurio.RequestCreateDinosaurioDto;
import es.cursojee.jurassicpark.controller.dto.dinosaurio.RequestDeleteDinosaurioDto;
import es.cursojee.jurassicpark.controller.dto.dinosaurio.RequestUpdateDinosaurioDto;
import es.cursojee.jurassicpark.controller.dto.dinosaurio.ResponseDinosaurioDto;
import es.cursojee.jurassicpark.exception.DinosaurioElementNotFoundException;

public interface DinosaurioManagementService {
	static final String BEAN_NAME ="dinosaurioManagementService";
	
	List<ResponseDinosaurioDto> findAll();
	ResponseDinosaurioDto findById(Long id) throws DinosaurioElementNotFoundException;
	ResponseDinosaurioDto create(RequestCreateDinosaurioDto requestCreateDinosaurioDto);
	ResponseDinosaurioDto update(RequestUpdateDinosaurioDto requestUpdateDinosaurioDto);
	void delete(RequestDeleteDinosaurioDto requestDeleteDinosaurioDto);
	

}
