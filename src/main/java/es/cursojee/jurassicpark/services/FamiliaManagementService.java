package es.cursojee.jurassicpark.services;

import java.util.List;

import es.cursojee.jurassicpark.controller.dto.familia.RequestCreateFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.RequestDeleteFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.RequestUpdateFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.ResponseFamiliaDto;
import es.cursojee.jurassicpark.exception.DinosaurioElementNotFoundException;
import es.cursojee.jurassicpark.exception.IntegratedForeignKeyException;
import es.cursojee.jurassicpark.exception.NotConfirmDeleteDinosaurio;

public interface FamiliaManagementService {

	static final String BEAN_NAME ="familiaManagementService";
	List<ResponseFamiliaDto> findAll();
	ResponseFamiliaDto findById(Long id) throws DinosaurioElementNotFoundException;
	ResponseFamiliaDto create(RequestCreateFamiliaDto requestCreateFamiliaDto);
	ResponseFamiliaDto update(RequestUpdateFamiliaDto requestUpdateFamiliaDto) throws DinosaurioElementNotFoundException;
	void delete(RequestDeleteFamiliaDto requestDeleteFamiliaDto) throws NotConfirmDeleteDinosaurio, DinosaurioElementNotFoundException, IntegratedForeignKeyException;
}
