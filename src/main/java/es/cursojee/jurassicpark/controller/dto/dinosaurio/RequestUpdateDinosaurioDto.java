package es.cursojee.jurassicpark.controller.dto.dinosaurio;

import es.cursojee.jurassicpark.model.sexo.Sexo;
import lombok.Data;
@Data
public class RequestUpdateDinosaurioDto {
	private Long id;
	private String nombre;
	private Sexo sexo;
	private Long idEspecie;
	private Long idRecinto;
}
