package es.cursojee.jurassicpark.services.basic;

import java.util.List;

import es.cursojee.jurassicpark.model.Recinto;

public interface RecintoService {

	static final String BEAN_NAME ="recintoService";
	List<Recinto>findAll();
	Recinto findById( Long id);
	Recinto create(Recinto recinto);
	Recinto update(Recinto recinto);
	void delete(Recinto recinto);
	
}
