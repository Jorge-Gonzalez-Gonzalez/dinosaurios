package es.cursojee.jurassicpark.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.cursojee.jurassicpark.model.Especie;

@Repository
public interface EspecieRepository extends JpaRepository<Especie,Long>{
	@Query("select e from Especie e where e.familia.id = ?1")
	List<Especie>findByFamilia(Long id);
}
