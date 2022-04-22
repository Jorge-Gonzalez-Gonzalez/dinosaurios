package es.cursojee.jurassicpark.controller.dto.especie;

import lombok.Data;

@Data
public class RequestDeleteEspecieDto {
	private Long id;
	private Boolean confirmacion;
}
