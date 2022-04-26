package es.cursojee.jurassicpark.services;


import java.util.List;
import es.cursojee.jurassicpark.controller.dto.especie.RequestCreateEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.RequestDeleteEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.RequestUpdateEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.ResponseEspecieDto;
import es.cursojee.jurassicpark.exception.DinosaurioElementNotFoundException;
import es.cursojee.jurassicpark.exception.NotConfirmDeleteDinosaurio;
import es.cursojee.jurassicpark.model.Especie;

public interface EspecieManagementService {
	
	static final String BEAN_NAME ="especieManagementService";
	
	List<ResponseEspecieDto> findAll();
	ResponseEspecieDto findEspecieById(Long id) throws DinosaurioElementNotFoundException;
	ResponseEspecieDto create(RequestCreateEspecieDto requestCreateEspecieDto) throws DinosaurioElementNotFoundException;
	ResponseEspecieDto update(RequestUpdateEspecieDto requestUpdateEspecieDto) throws DinosaurioElementNotFoundException;
	void delete(RequestDeleteEspecieDto requestDeleteEspecieDto) throws DinosaurioElementNotFoundException, NotConfirmDeleteDinosaurio;
	Especie findById(Long id) throws DinosaurioElementNotFoundException;
	public List<Especie>findByIdFamilia(Long id);
}
