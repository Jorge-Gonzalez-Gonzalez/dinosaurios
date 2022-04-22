package es.cursojee.jurassicpark.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.cursojee.jurassicpark.model.Familia;

@Repository
public interface FamiliaRepository extends JpaRepository<Familia,Long>{

}
