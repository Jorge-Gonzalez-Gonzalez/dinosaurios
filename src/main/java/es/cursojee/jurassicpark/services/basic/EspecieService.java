package es.cursojee.jurassicpark.services.basic;

import java.util.List;

import es.cursojee.jurassicpark.model.Especie;

public interface EspecieService {

	static final String BEAN_NAME ="especieService";
	
	List<Especie> findAll();
	Especie findById(Long id);
	Especie create(Especie especie);
	Especie update(Especie especie);
	void delete(Especie especie);
	List<Especie> findByIdFamilia(Long id);
	
}
