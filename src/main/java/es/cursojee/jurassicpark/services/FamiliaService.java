package es.cursojee.jurassicpark.services;

import java.util.List;

import es.cursojee.jurassicpark.controller.dto.familia.RequestCreateFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.RequestDeleteFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.RequestUpdateFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.ResponseFamiliaDto;
import es.cursojee.jurassicpark.exception.DinosaurioElementNotFoundException;
import es.cursojee.jurassicpark.exception.NotConfirmDeleteDinosaurio;
import es.cursojee.jurassicpark.model.Familia;

public interface FamiliaService {

	static final String BEAN_NAME ="familiaService";
	List<ResponseFamiliaDto> findAll();
	ResponseFamiliaDto findFamiliaById(Long id) throws DinosaurioElementNotFoundException;
	ResponseFamiliaDto create(RequestCreateFamiliaDto requestCreateFamiliaDto);
	ResponseFamiliaDto update(RequestUpdateFamiliaDto requestUpdateFamiliaDto) throws DinosaurioElementNotFoundException;
	void delete(RequestDeleteFamiliaDto requestDeleteFamiliaDto) throws NotConfirmDeleteDinosaurio, DinosaurioElementNotFoundException;
	Familia findById(Long id) throws DinosaurioElementNotFoundException;
}
