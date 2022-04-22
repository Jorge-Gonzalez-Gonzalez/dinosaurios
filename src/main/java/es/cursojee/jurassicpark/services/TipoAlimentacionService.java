package es.cursojee.jurassicpark.services;

import es.cursojee.jurassicpark.model.TipoAlimentacion;

public interface TipoAlimentacionService {
	
	static final String BEAN_NAME ="tipoAlimentacionService";
	TipoAlimentacion findById(Long id);
}
