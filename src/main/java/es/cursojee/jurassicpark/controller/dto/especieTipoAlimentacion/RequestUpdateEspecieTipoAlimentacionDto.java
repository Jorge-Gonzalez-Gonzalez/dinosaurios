package es.cursojee.jurassicpark.controller.dto.especieTipoAlimentacion;

import lombok.Data;

@Data
public class RequestUpdateEspecieTipoAlimentacionDto {
	private Long id;
	private Long idEspecie;
	private Long idTipoAlimentacion;

}
