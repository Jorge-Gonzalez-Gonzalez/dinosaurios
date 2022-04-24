package es.cursojee.jurassicpark.controller.dto.tipoAlimentacion;

import lombok.Data;

@Data
public class RequestDeleteTipoAlimentacionDto {
	private Long id;
	private Boolean confirmacion;
}
