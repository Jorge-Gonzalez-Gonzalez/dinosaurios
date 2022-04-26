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
import es.cursojee.jurassicpark.services.EspecieManagementService;
import es.cursojee.jurassicpark.services.EspecieTipoAlimentacionManagementService;
import es.cursojee.jurassicpark.services.FamiliaManagementService;
import es.cursojee.jurassicpark.services.TipoAlimentacionManagementService;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service(EspecieTipoAlimentacionManagementService.BEAN_NAME)
@Slf4j
public class EspecieTipoAlimentacionManagementServiceImpl implements EspecieTipoAlimentacionManagementService{

	@Autowired
	private EspecieTipoAlimentacionRepository especieTipoAlimentacionRepository;
	@Autowired
	private EspecieTipoAlimentacionMapper especieTipoAlimentacionMapper;
	@Autowired
	private EspecieManagementService especieService;
	@Autowired
	private TipoAlimentacionManagementService tipoAlimentacionService;
	
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
	
	@Override
	public EspecieTipoAlimentacion findByIdEspecie(Long id) {
		return especieTipoAlimentacionRepository.findByIdEspecie(id);
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
