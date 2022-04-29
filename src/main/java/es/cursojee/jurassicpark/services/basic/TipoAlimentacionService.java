package es.cursojee.jurassicpark.services.basic;

import java.util.List;

import es.cursojee.jurassicpark.model.TipoAlimentacion;

public interface TipoAlimentacionService {

	static final String BEAN_NAME ="tipoAlimentacionService";
	
	List<TipoAlimentacion> findAll();
	TipoAlimentacion findById(Long id);
	TipoAlimentacion create(TipoAlimentacion tipoAlimentacion);
	TipoAlimentacion update(TipoAlimentacion tipoAlimentacion);
	void delete(TipoAlimentacion tipoAlimentacion);
}
