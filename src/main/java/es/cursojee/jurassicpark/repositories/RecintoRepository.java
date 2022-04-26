package es.cursojee.jurassicpark.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.cursojee.jurassicpark.model.Recinto;

@Repository
public interface RecintoRepository extends JpaRepository<Recinto,Long>{

}
