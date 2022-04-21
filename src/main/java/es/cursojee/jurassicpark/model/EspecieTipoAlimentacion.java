package es.cursojee.jurassicpark.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "especie_tipo_alimentacion")
public class EspecieTipoAlimentacion  implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -6086190838393200561L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_esècie")
	private Especie especie;
	
	@ManyToOne
	@JoinColumn(name="id_tipo_alimentacion")
	private TipoAlimentacion tipoAlimentacion;

}
