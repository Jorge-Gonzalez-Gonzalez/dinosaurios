package es.cursojee.jurassicpark.services;

import java.util.List;

import es.cursojee.jurassicpark.controller.dto.dinosaurio.RequestCreateDinosaurioDto;
import es.cursojee.jurassicpark.controller.dto.dinosaurio.RequestDeleteDinosaurioDto;
import es.cursojee.jurassicpark.controller.dto.dinosaurio.RequestUpdateDinosaurioDto;
import es.cursojee.jurassicpark.controller.dto.dinosaurio.ResponseDinosaurioDto;
import es.cursojee.jurassicpark.exception.CompartirRecintoException;
import es.cursojee.jurassicpark.exception.DinosaurioElementNotFoundException;
import es.cursojee.jurassicpark.exception.NotConfirmDeleteDinosaurio;
import es.cursojee.jurassicpark.exception.RecintoNotFoundException;
import es.cursojee.jurassicpark.exception.SobrepasadoNumeroDinosauriosEnRecintoException;

public interface DinosaurioManagementService {
	static final String BEAN_NAME ="dinosaurioManagementService";
	
	List<ResponseDinosaurioDto> findAll();
	ResponseDinosaurioDto findById(Long id) throws DinosaurioElementNotFoundException;
	ResponseDinosaurioDto create(RequestCreateDinosaurioDto requestCreateDinosaurioDto) throws CompartirRecintoException, RecintoNotFoundException, SobrepasadoNumeroDinosauriosEnRecintoException, DinosaurioElementNotFoundException;
	ResponseDinosaurioDto update(RequestUpdateDinosaurioDto requestUpdateDinosaurioDto) throws DinosaurioElementNotFoundException, RecintoNotFoundException;
	void delete(RequestDeleteDinosaurioDto requestDeleteDinosaurioDto) throws NotConfirmDeleteDinosaurio, DinosaurioElementNotFoundException;
	

}
