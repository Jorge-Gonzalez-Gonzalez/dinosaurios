package es.cursojee.jurassicpark.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "recinto")
public class Recinto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4866877002349971091L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String tipoRecinto;
	
	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;
	
	@Column(name = "num_dinosaurios", nullable = false, length = 3)
	private Integer numDinosaurios;
	
	
}
