package es.cursojee.jurassicpark.services.basic;

import java.util.List;

import es.cursojee.jurassicpark.model.Dinosaurio;

public interface DinosaurioService {

	static final String BEAN_NAME ="dinosaurioService";
	List<Dinosaurio> findAll();
	Dinosaurio findById(Long id);
	Dinosaurio create(Dinosaurio dinosaurio);
	Dinosaurio update(Dinosaurio dinosaurio);
	void delete(Dinosaurio dinosaurio);
	List<Dinosaurio>findByRecinto(Long id);
}
