package es.cursojee.jurassicpark.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.cursojee.jurassicpark.model.Dinosaurio;

@Repository
public interface DinosaurioRepository extends JpaRepository<Dinosaurio,Long>{
	@Query("select d from Dinosaurio d where d.recinto.id = ?1")
	List<Dinosaurio>findByRecinto(Long id);
}
