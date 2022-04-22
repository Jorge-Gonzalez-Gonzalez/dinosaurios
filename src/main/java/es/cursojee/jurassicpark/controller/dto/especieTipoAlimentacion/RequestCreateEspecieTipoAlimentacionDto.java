package es.cursojee.jurassicpark.controller.dto.especieTipoAlimentacion;

import lombok.Data;

@Data
public class RequestCreateEspecieTipoAlimentacionDto {
	private Long idEspecie;
	private Long idTipoAlimentacion;
}
