package es.cursojee.jurassicpark.controller.dto.recinto;

import lombok.Data;

@Data
public class ResponseRecintoDto {

	private Long id;
	private String nombre;
	private String tipoRecinto;
	private Integer numDinosaurios;
	
}
