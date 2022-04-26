package es.cursojee.jurassicpark.controller.dto.especie;

import es.cursojee.jurassicpark.model.tipoPeligrosidad.CodigoTipoPeligrosidad;

import lombok.Data;
@Data
public class ResponseEspecieDto {
	
	private Long id;
	private String nombre;
	CodigoTipoPeligrosidad codigoTipoPeligrosidad;
	private Integer longitud;
	private Integer anioDesde;
	private Integer anioHasta;
	private Long idFamilia;
}
