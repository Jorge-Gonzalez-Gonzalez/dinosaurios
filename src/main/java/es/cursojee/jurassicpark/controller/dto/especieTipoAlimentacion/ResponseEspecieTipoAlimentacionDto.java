package es.cursojee.jurassicpark.controller.dto.especieTipoAlimentacion;

import lombok.Data;

@Data
public class ResponseEspecieTipoAlimentacionDto {
	private Long id;
	private Long idEspecie;
	private Long idTipoAlimentacion;
}
