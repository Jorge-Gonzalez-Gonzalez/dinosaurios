package es.cursojee.jurassicpark.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.cursojee.jurassicpark.model.Especie;

@Repository
public interface EspecieRepository extends JpaRepository<Especie,Long>{

}
