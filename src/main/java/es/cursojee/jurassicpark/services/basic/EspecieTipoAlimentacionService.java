package es.cursojee.jurassicpark.services.basic;

import java.util.List;

import es.cursojee.jurassicpark.model.EspecieTipoAlimentacion;

public interface EspecieTipoAlimentacionService {

	static final String BEAN_NAME ="especieTipoAlimentacionService";
	
	List<EspecieTipoAlimentacion>findAll();
	EspecieTipoAlimentacion findById(Long id);
	EspecieTipoAlimentacion create(EspecieTipoAlimentacion especieTipoAlimentacion);
	EspecieTipoAlimentacion update(EspecieTipoAlimentacion especieTipoAlimentacion);
	void delete(EspecieTipoAlimentacion especieTipoAlimentacion);
	EspecieTipoAlimentacion findByIdEspecie(Long id);
	List<EspecieTipoAlimentacion> findByIdAlimentacion(Long id);
}
