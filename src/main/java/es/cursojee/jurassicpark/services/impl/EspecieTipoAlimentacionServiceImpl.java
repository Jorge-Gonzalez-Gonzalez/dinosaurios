package es.cursojee.jurassicpark.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cursojee.jurassicpark.controller.dto.especieTipoAlimentacion.RequestCreateEspecieTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.especieTipoAlimentacion.RequestDeleteEspecieTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.especieTipoAlimentacion.RequestUpdateEspecieTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.especieTipoAlimentacion.ResponseEspecieTipoAlimentacionDto;
import es.cursojee.jurassicpark.exception.DinosaurioElementNotFoundException;
import es.cursojee.jurassicpark.exception.NotConfirmDeleteDinosaurio;
import es.cursojee.jurassicpark.mapper.EspecieTipoAlimentacionMapper;
import es.cursojee.jurassicpark.model.Especie;
import es.cursojee.jurassicpark.model.EspecieTipoAlimentacion;
import es.cursojee.jurassicpark.model.TipoAlimentacion;
import es.cursojee.jurassicpark.repositories.EspecieTipoAlimentacionRepository;
import es.cursojee.jurassicpark.services.EspecieService;
import es.cursojee.jurassicpark.services.EspecieTipoAlimentacionService;
import es.cursojee.jurassicpark.services.FamiliaService;
import es.cursojee.jurassicpark.services.TipoAlimentacionService;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service(FamiliaService.BEAN_NAME)
@Slf4j
public class EspecieTipoAlimentacionServiceImpl implements EspecieTipoAlimentacionService{

	@Autowired
	private EspecieTipoAlimentacionRepository especieTipoAlimentacionRepository;
	@Autowired
	private EspecieTipoAlimentacionMapper especieTipoAlimentacionMapper;
	@Autowired
	private EspecieService especieService;
	@Autowired
	private TipoAlimentacionService tipoAlimentacionService;
	
	@Override
	public List<ResponseEspecieTipoAlimentacionDto> findAll() {
		// TODO Auto-generated method stub
		List<EspecieTipoAlimentacion> especieTipoAlimentacion = especieTipoAlimentacionRepository.findAll();
		return especieTipoAlimentacionMapper.listEspecieTipoAlimentacionToListResponseEspecieTipoAlimentacionDto(especieTipoAlimentacion);
		
	}
	@Override
	public ResponseEspecieTipoAlimentacionDto findEspecieTipoAlimentacionById(Long id) throws DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		EspecieTipoAlimentacion especieTipoAlimentacion = findById(id);
		return especieTipoAlimentacionMapper.especieTipoAlimentacionToResponseEspecieTipoAlimentacionDto(especieTipoAlimentacion);
	}
		
	@Override
	public ResponseEspecieTipoAlimentacionDto create(RequestCreateEspecieTipoAlimentacionDto requestCreateEspecieTipoAlimentacionDto) throws DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		Especie especie = especieService.findById(requestCreateEspecieTipoAlimentacionDto.getIdEspecie());
		TipoAlimentacion tipoAlimentacion = tipoAlimentacionService.findById(requestCreateEspecieTipoAlimentacionDto.getIdTipoAlimentacion());
		
		EspecieTipoAlimentacion newEspecieTipoAlimentacion = especieTipoAlimentacionMapper.requestCreateEspecieTipoAlimentacionDtoToEspecieTipoAlimentacion(requestCreateEspecieTipoAlimentacionDto);
		newEspecieTipoAlimentacion = especieTipoAlimentacionRepository.save(newEspecieTipoAlimentacion);
		return especieTipoAlimentacionMapper.especieTipoAlimentacionToResponseEspecieTipoAlimentacionDto(newEspecieTipoAlimentacion);
	}

	@Override
	public ResponseEspecieTipoAlimentacionDto update(
			RequestUpdateEspecieTipoAlimentacionDto requestUpdateEspecieTipoAlimentacionDto) throws DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		Especie especie = especieService.findById(requestUpdateEspecieTipoAlimentacionDto.getIdEspecie());
		TipoAlimentacion tipoAlimentacion = tipoAlimentacionService.findById(requestUpdateEspecieTipoAlimentacionDto.getIdTipoAlimentacion());
		EspecieTipoAlimentacion especieTipoAlimentacion = findById(requestUpdateEspecieTipoAlimentacionDto.getId());
		
		especieTipoAlimentacion = especieTipoAlimentacionMapper.requestUpdateEspecieTipoAlimentacionDtoToEspecieTipoAlimentacion(requestUpdateEspecieTipoAlimentacionDto, especieTipoAlimentacion);
		especieTipoAlimentacionRepository.save(especieTipoAlimentacion);
		
		return especieTipoAlimentacionMapper.especieTipoAlimentacionToResponseEspecieTipoAlimentacionDto(especieTipoAlimentacion);
	}

	@Override
	public void delete(RequestDeleteEspecieTipoAlimentacionDto requestDeleteEspecieTipoAlimentacionDto) throws NotConfirmDeleteDinosaurio, DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		
		if(!requestDeleteEspecieTipoAlimentacionDto.getConfirmacion()) {
			throw new NotConfirmDeleteDinosaurio("No se ha confirmado el borrado de EspecieTipoAlimentación");
		}
		EspecieTipoAlimentacion especieTipoAlimentacion = findById(requestDeleteEspecieTipoAlimentacionDto.getId());
		especieTipoAlimentacionRepository.delete(especieTipoAlimentacion);		
	}
	
	public EspecieTipoAlimentacion findById(Long id) throws DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		EspecieTipoAlimentacion especieTipoAlimentacion = especieTipoAlimentacionRepository.getById(id);
		if(especieTipoAlimentacion == null) {
			throw new DinosaurioElementNotFoundException("No se ha encontrado nunguna EspecieTipoAlimentacion con ese id");
		}
		
		return especieTipoAlimentacion;
	}

	

}
