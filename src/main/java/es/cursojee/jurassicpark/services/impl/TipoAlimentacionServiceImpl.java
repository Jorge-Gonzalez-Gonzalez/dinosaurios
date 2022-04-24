package es.cursojee.jurassicpark.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cursojee.jurassicpark.controller.dto.tipoAlimentacion.RequestCreateTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.tipoAlimentacion.RequestDeleteTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.tipoAlimentacion.RequestUpdateTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.tipoAlimentacion.ResponseTipoAlimentacionDto;
import es.cursojee.jurassicpark.exception.DinosaurioElementNotFoundException;
import es.cursojee.jurassicpark.exception.NotConfirmDeleteDinosaurio;
import es.cursojee.jurassicpark.mapper.TipoAlimentacionMapper;
import es.cursojee.jurassicpark.model.TipoAlimentacion;
import es.cursojee.jurassicpark.repositories.TipoAlimentacionRepository;
import es.cursojee.jurassicpark.services.FamiliaService;
import es.cursojee.jurassicpark.services.TipoAlimentacionService;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service(FamiliaService.BEAN_NAME)
@Slf4j
public class TipoAlimentacionServiceImpl implements TipoAlimentacionService{
	
	@Autowired
	private TipoAlimentacionRepository tipoAlimentacionRepository;
	@Autowired
	private TipoAlimentacionMapper tipoAlimentacionMapper;
	
	@Override
	public List<ResponseTipoAlimentacionDto> findAll() {
		// TODO Auto-generated method stub
		
		List<TipoAlimentacion> listaAlimentacion = tipoAlimentacionRepository.findAll();
		return tipoAlimentacionMapper.listTipoAlimentacionToListResponseTipoAlimentacionDto(listaAlimentacion);
	}
	
	@Override
	public ResponseTipoAlimentacionDto findTipoAlimentacionById(Long id) throws DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		TipoAlimentacion tipoAlimentacion = findById(id);
		return tipoAlimentacionMapper.tipoAlimentacionToResponseTipoAlimentacion(tipoAlimentacion);
	}
	
	@Override
	public ResponseTipoAlimentacionDto create(RequestCreateTipoAlimentacionDto requestCreateTipoAlimentacionDto) {
		// TODO Auto-generated method stub
		TipoAlimentacion alimentacion = tipoAlimentacionMapper.requestCreateTipoAlimentacionDtoToTipoAlimentacion(requestCreateTipoAlimentacionDto);
		alimentacion = tipoAlimentacionRepository.save(alimentacion);
		return tipoAlimentacionMapper.tipoAlimentacionToResponseTipoAlimentacion(alimentacion);
	}
	
	@Override
	public ResponseTipoAlimentacionDto update(RequestUpdateTipoAlimentacionDto requestUpdateTipoAlimentacionDto) throws DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		TipoAlimentacion alimentacion = findById(requestUpdateTipoAlimentacionDto.getId());
		alimentacion = tipoAlimentacionMapper.RequestUpdateTipoAlimentacionDto(requestUpdateTipoAlimentacionDto, alimentacion);
		alimentacion = tipoAlimentacionRepository.save(alimentacion);
		return tipoAlimentacionMapper.tipoAlimentacionToResponseTipoAlimentacion(alimentacion);
	}
	
	@Override
	public void delete(RequestDeleteTipoAlimentacionDto requestDeleteTipoAlimentacionDto) throws NotConfirmDeleteDinosaurio, DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		if(!requestDeleteTipoAlimentacionDto.getConfirmacion()) {
			throw new NotConfirmDeleteDinosaurio("No se ha confirmado el borrado de la Alimentación");
		}
		TipoAlimentacion alimentacion = findById(requestDeleteTipoAlimentacionDto.getId());
		tipoAlimentacionRepository.delete(alimentacion);
		
	}
	
	@Override
	public TipoAlimentacion findById(Long id) throws DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		TipoAlimentacion alimentacion = tipoAlimentacionRepository.getById(id);
		if(alimentacion == null) {
			throw new DinosaurioElementNotFoundException("No se ha encontrado el tipo de alimentacion con ese id");
		}
		return alimentacion;
	}

}
