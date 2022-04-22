package es.cursojee.jurassicpark.services;

import es.cursojee.jurassicpark.controller.dto.familia.RequestCreateFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.RequestDeleteFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.ResponseFamiliaDto;
import es.cursojee.jurassicpark.model.Familia;

public interface FamiliaService {

	static final String BEAN_NAME ="familiaService";
	
	Familia findById(Long id);
	
	ResponseFamiliaDto create(RequestCreateFamiliaDto requestCreateFamiliaDto);
	void delete(RequestDeleteFamiliaDto requestDeleteFamiliaDto);
	
}
