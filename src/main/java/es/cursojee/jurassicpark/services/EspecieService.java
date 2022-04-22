package es.cursojee.jurassicpark.services;


import es.cursojee.jurassicpark.controller.dto.especie.RequestCreateEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.RequestDeleteEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.ResponseEspecieDto;
import es.cursojee.jurassicpark.model.Especie;

public interface EspecieService {
	
	static final String BEAN_NAME ="especieService";
	
	Especie findById(Long id);
	ResponseEspecieDto create(RequestCreateEspecieDto requestCreateEspecieDto);
	void delete(RequestDeleteEspecieDto requestDeleteEspecieDto);
	
}
