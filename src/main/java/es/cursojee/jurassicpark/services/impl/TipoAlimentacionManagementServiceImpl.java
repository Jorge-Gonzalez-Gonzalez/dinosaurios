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
import es.cursojee.jurassicpark.exception.IntegratedForeignKeyException;
import es.cursojee.jurassicpark.exception.NotConfirmDeleteDinosaurio;
import es.cursojee.jurassicpark.mapper.TipoAlimentacionMapper;
import es.cursojee.jurassicpark.model.EspecieTipoAlimentacion;
import es.cursojee.jurassicpark.model.TipoAlimentacion;
import es.cursojee.jurassicpark.services.EspecieTipoAlimentacionManagementService;
import es.cursojee.jurassicpark.services.TipoAlimentacionManagementService;
import es.cursojee.jurassicpark.services.basic.TipoAlimentacionService;

@Transactional
@Service(TipoAlimentacionManagementService.BEAN_NAME)
public class TipoAlimentacionManagementServiceImpl implements TipoAlimentacionManagementService{
	
	@Autowired
	private TipoAlimentacionService tipoAlimentacionService;
	@Autowired
	private TipoAlimentacionMapper tipoAlimentacionMapper;
	@Autowired
	private EspecieTipoAlimentacionManagementService especieTipoAlimentacionManagementService;
	
	@Override
	public List<ResponseTipoAlimentacionDto> findAll() {
		// TODO Auto-generated method stub
		List<TipoAlimentacion> listaAlimentacion = tipoAlimentacionService.findAll();
		return tipoAlimentacionMapper.listTipoAlimentacionToListResponseTipoAlimentacionDto(listaAlimentacion);
	}
	
	@Override
	public ResponseTipoAlimentacionDto findById(Long id) throws DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		TipoAlimentacion tipoAlimentacion = tipoAlimentacionService.findById(id);
		
		if(tipoAlimentacion == null) {
			throw new DinosaurioElementNotFoundException("No existe ese tipo de alimentación");
		}
		
		return tipoAlimentacionMapper.tipoAlimentacionToResponseTipoAlimentacion(tipoAlimentacion);
	}
	
	@Override
	public ResponseTipoAlimentacionDto create(RequestCreateTipoAlimentacionDto requestCreateTipoAlimentacionDto) {
		// TODO Auto-generated method stub
		TipoAlimentacion alimentacion = tipoAlimentacionMapper.requestCreateTipoAlimentacionDtoToTipoAlimentacion(requestCreateTipoAlimentacionDto);
		alimentacion = tipoAlimentacionService.create(alimentacion);
		return tipoAlimentacionMapper.tipoAlimentacionToResponseTipoAlimentacion(alimentacion);
	}
	
	@Override
	public ResponseTipoAlimentacionDto update(RequestUpdateTipoAlimentacionDto requestUpdateTipoAlimentacionDto) throws DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		TipoAlimentacion alimentacion = tipoAlimentacionService.findById(requestUpdateTipoAlimentacionDto.getId());
		
		if(alimentacion == null) {
			throw new DinosaurioElementNotFoundException("No existe ese tipo de alimentación");
		}
		
		alimentacion = tipoAlimentacionMapper.RequestUpdateTipoAlimentacionDto(requestUpdateTipoAlimentacionDto, alimentacion);
		alimentacion = tipoAlimentacionService.update(alimentacion);
		return tipoAlimentacionMapper.tipoAlimentacionToResponseTipoAlimentacion(alimentacion);
	}
	
	@Override
	public void delete(RequestDeleteTipoAlimentacionDto requestDeleteTipoAlimentacionDto) throws NotConfirmDeleteDinosaurio, DinosaurioElementNotFoundException, IntegratedForeignKeyException {
		// TODO Auto-generated method stub
		if(!requestDeleteTipoAlimentacionDto.getConfirmacion()) {
			throw new NotConfirmDeleteDinosaurio("No se ha confirmado el borrado de la Alimentación");
		}
		
		List<EspecieTipoAlimentacion> listaEspecieTipoAlimentacion = especieTipoAlimentacionManagementService.findByIdAlimentacion(requestDeleteTipoAlimentacionDto.getId());
		
		if(!listaEspecieTipoAlimentacion.isEmpty()) {
			throw new IntegratedForeignKeyException("Esta familia tiene Alimentaciones asociadas y no se puede borrar");
		}
		
		TipoAlimentacion deleteTipoAlimentacion = tipoAlimentacionService.findById(requestDeleteTipoAlimentacionDto.getId());
		
		if(deleteTipoAlimentacion == null) {
			throw new DinosaurioElementNotFoundException("No existe ese tipo de alimentación");
		}
		
		tipoAlimentacionService.delete(deleteTipoAlimentacion);
		
	}
	
	
}
