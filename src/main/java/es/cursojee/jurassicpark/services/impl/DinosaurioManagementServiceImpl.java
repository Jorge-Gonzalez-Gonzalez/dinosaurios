package es.cursojee.jurassicpark.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cursojee.jurassicpark.controller.dto.dinosaurio.RequestCreateDinosaurioDto;
import es.cursojee.jurassicpark.controller.dto.dinosaurio.RequestDeleteDinosaurioDto;
import es.cursojee.jurassicpark.controller.dto.dinosaurio.RequestUpdateDinosaurioDto;
import es.cursojee.jurassicpark.controller.dto.dinosaurio.ResponseDinosaurioDto;
import es.cursojee.jurassicpark.controller.dto.recinto.RequestUpdateRecintoDto;
import es.cursojee.jurassicpark.controller.dto.recinto.ResponseRecintoDto;
import es.cursojee.jurassicpark.exception.DinosaurioElementNotFoundException;
import es.cursojee.jurassicpark.mapper.DinosaurioMapper;
import es.cursojee.jurassicpark.mapper.RecintoMapper;
import es.cursojee.jurassicpark.model.Dinosaurio;
import es.cursojee.jurassicpark.model.EspecieTipoAlimentacion;
import es.cursojee.jurassicpark.model.Recinto;
import es.cursojee.jurassicpark.model.TipoAlimentacion;
import es.cursojee.jurassicpark.services.DinosaurioManagementService;
import es.cursojee.jurassicpark.services.EspecieManagementService;
import es.cursojee.jurassicpark.services.EspecieTipoAlimentacionManagementService;
import es.cursojee.jurassicpark.services.RecintoManagementService;
import es.cursojee.jurassicpark.services.TipoAlimentacionManagementService;
import es.cursojee.jurassicpark.services.basic.DinosaurioService;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service(EspecieManagementService.BEAN_NAME)
@Slf4j
public class DinosaurioManagementServiceImpl implements DinosaurioManagementService {
	
	@Autowired
	private DinosaurioService dinosaurioService;
	
	@Autowired
	private DinosaurioMapper dinosaurioMapper;
	
	@Autowired
	private RecintoMapper recintoMapper;
	
	@Autowired
	private EspecieTipoAlimentacionManagementService especieTipoAlimentacionService;
	
	@Autowired
	private TipoAlimentacionManagementService tipoAlimentacionService;
	
	@Autowired
	private RecintoManagementService recintoManagementService;
	
	@Override
	public List<ResponseDinosaurioDto> findAll() {
		// TODO Auto-generated method stub
		List<Dinosaurio> listaDinosaurios = dinosaurioService.findAll();
		return dinosaurioMapper.listaDinosaurioToListaResponseDinosaurioDto(listaDinosaurios);
	}

	@Override
	public ResponseDinosaurioDto findById(Long id) throws DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		Dinosaurio dinosaurio = dinosaurioService.findById(id);
		if(dinosaurio == null) {
			throw new DinosaurioElementNotFoundException("No exxiste el dinosaurio con ese id");
		}
		return dinosaurioMapper.dinosaurioToResponseDinosaurioDto(dinosaurio);
	}

	@Override
	public ResponseDinosaurioDto create(RequestCreateDinosaurioDto requestCreateDinosaurioDto) {
		// TODO Auto-generated method stub
		EspecieTipoAlimentacion especieAlimentacion = especieTipoAlimentacionService.findByIdEspecie(requestCreateDinosaurioDto.getIdEspecie());
		TipoAlimentacion tipoAlimentacion = tipoAlimentacionService.findById(especieAlimentacion.getTipoAlimentacion().getId());
		ResponseRecintoDto responseRecinto = recintoManagementService.findById(requestCreateDinosaurioDto.getIdRecinto());
		Dinosaurio newDinosaurio = null;
		
		if(!tipoAlimentacion.getDescripcion().equals(responseRecinto.getTipoReciento())) {
			throw new CompartirRecintoException("No se pueden juntar dinosaurios carnivoros y Herbívoros en el mismo recinto");
		}
		
		if(responseRecinto.getNumDinosaurios()>4) {
			
		}
		
		newDinosaurio = dinosaurioMapper.requestCreatetDinosaurioDtoToDinosaurio(requestCreateDinosaurioDto);
		newDinosaurio = dinosaurioService.create(newDinosaurio);
		
		Recinto recinto = recintoMapper.responseRecintoDtoToRecinto(responseRecinto);
		recinto.setNumDinosaurios(recinto.getNumDinosaurios() + 1);
		
		RequestUpdateRecintoDto updateRecinto = recintoMapper.recintoToRequestUpdateRecintoDto(recinto);
		recintoManagementService.update(updateRecinto);
		
		
		
		
		
		
		return dinosaurioMapper.dinosaurioToResponseDinosaurioDto(newDinosaurio);
	}

	@Override
	public ResponseDinosaurioDto update(RequestUpdateDinosaurioDto requestUpdateDinosaurioDto) {
		// TODO Auto-generated method stub
		Dinosaurio updateDinosaurio = dinosaurioService.findById(requestUpdateDinosaurioDto.getId());
		if(updateDinosaurio == null) {
			
		}
		updateDinosaurio = dinosaurioMapper.requestUpdateDinosaurioDtoToDinosaurio(requestUpdateDinosaurioDto, updateDinosaurio);
		updateDinosaurio = dinosaurioService.update(updateDinosaurio);
		
		return dinosaurioMapper.dinosaurioToResponseDinosaurioDto(updateDinosaurio);
	}

	@Override
	public void delete(RequestDeleteDinosaurioDto requestDeleteDinosaurioDto) {
		// TODO Auto-generated method stub
		
	}

}
