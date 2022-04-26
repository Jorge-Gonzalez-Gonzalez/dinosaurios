package es.cursojee.jurassicpark.services.basic;

import java.util.List;

import es.cursojee.jurassicpark.model.Familia;

public interface FamiliaService {
	
	static final String BEAN_NAME ="familiaService";
	
	List<Familia> findAll();
	Familia findById(Long id);
	Familia create(Familia familia);
	Familia update(Familia familia);
	void delete(Familia familia);
	
}
