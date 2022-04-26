package es.cursojee.jurassicpark.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.cursojee.jurassicpark.model.tipoPeligrosidad.CodigoTipoPeligrosidad;
import lombok.Data;

@Data
@Entity
@Table(name = "especie")
public class Especie implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 4721137392990419441L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "codigo_tipo_peligrosidad", nullable = false, length = 20)
	private CodigoTipoPeligrosidad codigoTipoPeligrosidad;
	
	@Column(name = "longitud", nullable = false, length = 3)
	private Integer longitud;
	
	@Column(name = "anio_desde", nullable = true, length = 3)
	private Integer anioDesde;
	
	@Column(name = "anio_hasta", nullable = true, length = 3)
	private Integer anioHasta;
	
	@ManyToOne
	@JoinColumn(name="id_familia")
	private Familia familia;
	

}
