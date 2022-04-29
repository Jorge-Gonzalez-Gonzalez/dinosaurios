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
import es.cursojee.jurassicpark.services.EspecieTipoAlimentacionManagementService;
import es.cursojee.jurassicpark.services.basic.EspecieService;
import es.cursojee.jurassicpark.services.basic.EspecieTipoAlimentacionService;
import es.cursojee.jurassicpark.services.basic.TipoAlimentacionService;

@Transactional
@Service(EspecieTipoAlimentacionManagementService.BEAN_NAME)
public class EspecieTipoAlimentacionManagementServiceImpl implements EspecieTipoAlimentacionManagementService{

	@Autowired
	private EspecieTipoAlimentacionService especieTipoAlimentacionService;
	@Autowired
	private EspecieTipoAlimentacionMapper especieTipoAlimentacionMapper;
	@Autowired
	private EspecieService especieService;
	
	@Autowired
	private TipoAlimentacionService tipoAlimentacionService;
	
	@Override
	public List<ResponseEspecieTipoAlimentacionDto> findAll() {
		// TODO Auto-generated method stub
		List<EspecieTipoAlimentacion> especieTipoAlimentacion = especieTipoAlimentacionService.findAll();
		return especieTipoAlimentacionMapper.listEspecieTipoAlimentacionToListResponseEspecieTipoAlimentacionDto(especieTipoAlimentacion);
		
	}
	@Override
	public ResponseEspecieTipoAlimentacionDto findById(Long id) throws DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		EspecieTipoAlimentacion especieTipoAlimentacion = especieTipoAlimentacionService.findById(id);

		if(especieTipoAlimentacion == null) {
			throw new DinosaurioElementNotFoundException(" No existe EspecieTipoAlimentacion");
		}
		
		ResponseEspecieTipoAlimentacionDto response = especieTipoAlimentacionMapper.especieTipoAlimentacionToResponseEspecieTipoAlimentacionDto(especieTipoAlimentacion);
		response.setIdEspecie(especieTipoAlimentacion.getEspecie().getId());
		response.setIdTipoAlimentacion(especieTipoAlimentacion.getTipoAlimentacion().getId());
		return response;
	}
		
	@Override
	public ResponseEspecieTipoAlimentacionDto create(RequestCreateEspecieTipoAlimentacionDto requestCreateEspecieTipoAlimentacionDto) throws DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		EspecieTipoAlimentacion newEspecieTipoAlimentacion = especieTipoAlimentacionMapper.requestCreateEspecieTipoAlimentacionDtoToEspecieTipoAlimentacion(requestCreateEspecieTipoAlimentacionDto);
		Especie especie = especieService.findById(requestCreateEspecieTipoAlimentacionDto.getIdEspecie());
		TipoAlimentacion tipoAlimentacion = tipoAlimentacionService.findById(requestCreateEspecieTipoAlimentacionDto.getIdTipoAlimentacion());
	
		if(newEspecieTipoAlimentacion == null) {
			throw new DinosaurioElementNotFoundException(" No existe EspecieTipoAlimentacion");
		}
		
		if(especie == null || tipoAlimentacion==null) {
			throw new DinosaurioElementNotFoundException(" No existe ese tipo de alimentación o esa especie");
		}
		
		newEspecieTipoAlimentacion = especieTipoAlimentacionService.create(newEspecieTipoAlimentacion);
		
		ResponseEspecieTipoAlimentacionDto response = especieTipoAlimentacionMapper.especieTipoAlimentacionToResponseEspecieTipoAlimentacionDto(newEspecieTipoAlimentacion);
		response.setIdEspecie(especie.getId());
		response.setIdTipoAlimentacion(tipoAlimentacion.getId());
		return response;
	}

	@Override
	public ResponseEspecieTipoAlimentacionDto update(
			RequestUpdateEspecieTipoAlimentacionDto requestUpdateEspecieTipoAlimentacionDto) throws DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		EspecieTipoAlimentacion especieTipoAlimentacion = especieTipoAlimentacionService.findById(requestUpdateEspecieTipoAlimentacionDto.getId());
		Especie especie = especieService.findById(requestUpdateEspecieTipoAlimentacionDto.getIdEspecie());
		TipoAlimentacion tipoAlimentacion = tipoAlimentacionService.findById(requestUpdateEspecieTipoAlimentacionDto.getIdTipoAlimentacion());
		
		if(especieTipoAlimentacion == null) {
			throw new DinosaurioElementNotFoundException(" No existe EspecieTipoAlimentacion");
		}
		
		if(especie == null || tipoAlimentacion==null) {
			throw new DinosaurioElementNotFoundException(" No existe ese tipo de alimentación o esa especie");
		}
				
		especieTipoAlimentacion = especieTipoAlimentacionMapper.requestUpdateEspecieTipoAlimentacionDtoToEspecieTipoAlimentacion(requestUpdateEspecieTipoAlimentacionDto, especieTipoAlimentacion);
		especieTipoAlimentacion = especieTipoAlimentacionService.update(especieTipoAlimentacion);
		
		ResponseEspecieTipoAlimentacionDto response = especieTipoAlimentacionMapper.especieTipoAlimentacionToResponseEspecieTipoAlimentacionDto(especieTipoAlimentacion);
		response.setIdEspecie(especie.getId());
		response.setIdTipoAlimentacion(tipoAlimentacion.getId());
		return response;
		
	}

	@Override
	public void delete(RequestDeleteEspecieTipoAlimentacionDto requestDeleteEspecieTipoAlimentacionDto) throws NotConfirmDeleteDinosaurio, DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		
		if(!requestDeleteEspecieTipoAlimentacionDto.getConfirmacion()) {
			throw new NotConfirmDeleteDinosaurio("No se ha confirmado el borrado de EspecieTipoAlimentación");
		}
		
		EspecieTipoAlimentacion especieTipoAlimentacion = especieTipoAlimentacionService.findById(requestDeleteEspecieTipoAlimentacionDto.getId());
		
		if(especieTipoAlimentacion == null) {
			throw new DinosaurioElementNotFoundException(" No existe EspecieTipoAlimentacion");
		}
		
		especieTipoAlimentacionService.delete(especieTipoAlimentacion);		
	}
	
	@Override
	public EspecieTipoAlimentacion findByIdEspecie(Long id) {
		return especieTipoAlimentacionService.findByIdEspecie(id);
	}
	@Override
	public List<EspecieTipoAlimentacion> findByIdAlimentacion(Long id) {
		// TODO Auto-generated method stub
		List<EspecieTipoAlimentacion> listaEspecieTipoAlimentacion = especieTipoAlimentacionService.findByIdAlimentacion(id);
		return listaEspecieTipoAlimentacion;
	}
		

}
