package es.cursojee.jurassicpark.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.cursojee.jurassicpark.model.TipoAlimentacion;

@Repository
public interface TipoAlimentacionRepository extends JpaRepository<TipoAlimentacion,Long> {

}
