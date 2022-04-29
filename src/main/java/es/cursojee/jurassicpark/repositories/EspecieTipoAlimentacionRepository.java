package es.cursojee.jurassicpark.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.cursojee.jurassicpark.model.EspecieTipoAlimentacion;

@Repository
public interface EspecieTipoAlimentacionRepository extends JpaRepository<EspecieTipoAlimentacion,Long>{
	@Query("select eta from EspecieTipoAlimentacion eta where eta.especie.id = ?1")
	EspecieTipoAlimentacion findByIdEspecie(Long id);
	@Query("select eta from EspecieTipoAlimentacion eta where eta.tipoAlimentacion.id = ?1")
	List<EspecieTipoAlimentacion> findByIdTipoAlimentacion(Long id);
}
