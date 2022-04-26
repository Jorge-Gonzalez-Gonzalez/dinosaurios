package es.cursojee.jurassicpark.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.cursojee.jurassicpark.model.EspecieTipoAlimentacion;

@Repository
public interface EspecieTipoAlimentacionRepository extends JpaRepository<EspecieTipoAlimentacion,Long>{

	EspecieTipoAlimentacion findByIdEspecie(Long id);
}
