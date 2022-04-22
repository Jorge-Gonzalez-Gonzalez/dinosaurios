package es.cursojee.jurassicpark.services;

import es.cursojee.jurassicpark.controller.dto.especieTipoAlimentacion.RequestCreateEspecieTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.especieTipoAlimentacion.ResponseEspecieTipoAlimentacionDto;

public interface EspecieTipoAlimentacionService {

	static final String BEAN_NAME ="especieTipoAlimentacionService";
	ResponseEspecieTipoAlimentacionDto create(RequestCreateEspecieTipoAlimentacionDto requestCreateEspecieTipoAlimentacionDto);
}
