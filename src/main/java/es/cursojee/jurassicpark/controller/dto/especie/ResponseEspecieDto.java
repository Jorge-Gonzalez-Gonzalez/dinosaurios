package es.cursojee.jurassicpark.controller.dto.especie;

import es.cursojee.jurassicpark.model.tipoPeligrosidad.CodigoTipoPeligrosidad;

import lombok.Data;
@Data
public class ResponseEspecieDto {

	private String nombre;
	CodigoTipoPeligrosidad codigoTipoPeligrosidad;
	private Integer longitud;
	private Integer anio_desde;
	private Integer anio_hasta;
	private Long id_familia;
}
