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

import es.cursojee.jurassicpark.model.sexo.Sexo;
import es.cursojee.jurassicpark.model.tipoPeligrosidad.CodigoTipoPeligrosidad;
import lombok.Data;

@Data
@Entity
@Table(name = "dinosaurio")
public class Dinosaurio implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -866515423645457057L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "sexo", nullable = false, length = 20)
	private Sexo sexo;
	
	@ManyToOne
	@JoinColumn(name="id_especie")
	private Especie especie;
	
	@ManyToOne
	@JoinColumn(name="id_recinto")
	private Recinto reciento;
}
