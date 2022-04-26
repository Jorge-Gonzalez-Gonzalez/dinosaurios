package es.cursojee.jurassicpark.controller.dto.recinto;

import es.cursojee.jurassicpark.model.tipoRecinto.TipoReciento;
import lombok.Data;

@Data
public class RequestCreateRecintoDto {

	private String nombre;
	private TipoReciento tipoReciento;
	private Integer numDinosaurios;
}
