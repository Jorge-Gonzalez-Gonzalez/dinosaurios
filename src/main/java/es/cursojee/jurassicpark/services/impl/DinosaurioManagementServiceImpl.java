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
import es.cursojee.jurassicpark.exception.CompartirRecintoException;
import es.cursojee.jurassicpark.exception.DinosaurioElementNotFoundException;
import es.cursojee.jurassicpark.exception.NotConfirmDeleteDinosaurio;
import es.cursojee.jurassicpark.exception.RecintoNotFoundException;
import es.cursojee.jurassicpark.exception.SobrepasadoNumeroDinosauriosEnRecintoException;
import es.cursojee.jurassicpark.mapper.DinosaurioMapper;
import es.cursojee.jurassicpark.mapper.RecintoMapper;
import es.cursojee.jurassicpark.model.Dinosaurio;
import es.cursojee.jurassicpark.model.Especie;
import es.cursojee.jurassicpark.model.EspecieTipoAlimentacion;
import es.cursojee.jurassicpark.model.Recinto;
import es.cursojee.jurassicpark.model.TipoAlimentacion;
import es.cursojee.jurassicpark.services.DinosaurioManagementService;
import es.cursojee.jurassicpark.services.EspecieTipoAlimentacionManagementService;
import es.cursojee.jurassicpark.services.RecintoManagementService;
import es.cursojee.jurassicpark.services.basic.DinosaurioService;
import es.cursojee.jurassicpark.services.basic.EspecieService;
import es.cursojee.jurassicpark.services.basic.RecintoService;
import es.cursojee.jurassicpark.services.basic.TipoAlimentacionService;

@Transactional
@Service(DinosaurioManagementService.BEAN_NAME)

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
	private TipoAlimentacionService tipoAlimentacionService;
	
	@Autowired
	private EspecieService especieService;
	
	@Autowired
	private RecintoService recintoService;
	
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
		ResponseDinosaurioDto response =dinosaurioMapper.dinosaurioToResponseDinosaurioDto(dinosaurio);
		response.setIdEspecie(dinosaurio.getEspecie().getId());
		response.setIdRecinto(dinosaurio.getRecinto().getId());
		
		return response;
	}

	@Override
	public ResponseDinosaurioDto create(RequestCreateDinosaurioDto requestCreateDinosaurioDto) throws CompartirRecintoException, RecintoNotFoundException, SobrepasadoNumeroDinosauriosEnRecintoException, DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		Especie especie = especieService.findById(requestCreateDinosaurioDto.getIdEspecie());
		Recinto recinto = recintoService.findById(requestCreateDinosaurioDto.getIdRecinto());
		EspecieTipoAlimentacion especieAlimentacion = especieTipoAlimentacionService.findByIdEspecie(requestCreateDinosaurioDto.getIdEspecie());
		TipoAlimentacion tipoAlimentacion = tipoAlimentacionService.findById(especieAlimentacion.getTipoAlimentacion().getId());
		ResponseRecintoDto responseRecinto = recintoManagementService.findById(requestCreateDinosaurioDto.getIdRecinto());
		Dinosaurio newDinosaurio = null;
		
		if(especie == null) {
			throw new DinosaurioElementNotFoundException("No existe la especie");
		}
		
		if(recinto == null) {
			throw new RecintoNotFoundException("No existe el recinto");
		}
		
		
		if(!tipoAlimentacion.getDescripcion().equals(responseRecinto.getTipoRecinto())) {
			throw new CompartirRecintoException("No se pueden juntar dinosaurios carnivoros y Herbívoros en el mismo recinto");
		}
		
		if(responseRecinto.getNumDinosaurios()>=4) {
			throw new SobrepasadoNumeroDinosauriosEnRecintoException("Ya hay 4 dinosaurios en ese recinto");
		}
		
		newDinosaurio = dinosaurioMapper.requestCreatetDinosaurioDtoToDinosaurio(requestCreateDinosaurioDto);
		newDinosaurio = dinosaurioService.create(newDinosaurio);
		
		recinto = recintoMapper.responseRecintoDtoToRecinto(responseRecinto);
		recinto.setNumDinosaurios(recinto.getNumDinosaurios() + 1);
		
		RequestUpdateRecintoDto updateRecinto = recintoMapper.recintoToRequestUpdateRecintoDto(recinto);
		recintoManagementService.update(updateRecinto);
		
		ResponseDinosaurioDto response = dinosaurioMapper.dinosaurioToResponseDinosaurioDto(newDinosaurio);
		response.setIdEspecie(especie.getId());
		response.setIdRecinto(recinto.getId());
		
		return response;
	}

	@Override
	public ResponseDinosaurioDto update(RequestUpdateDinosaurioDto requestUpdateDinosaurioDto) throws DinosaurioElementNotFoundException, RecintoNotFoundException {
		// TODO Auto-generated method stub
		Dinosaurio updateDinosaurio = dinosaurioService.findById(requestUpdateDinosaurioDto.getId());
		Especie especie = especieService.findById(requestUpdateDinosaurioDto.getIdEspecie());
		Recinto recinto = recintoService.findById(requestUpdateDinosaurioDto.getIdRecinto());
				
		if(updateDinosaurio == null) {
			throw new DinosaurioElementNotFoundException("No existe el dinosaurio");
		}
	
		if(especie == null) {
			throw new DinosaurioElementNotFoundException("No existe la especie");
		}
		
		if(recinto == null) {
			throw new RecintoNotFoundException("No existe el recinto");
		}
		
		updateDinosaurio = dinosaurioMapper.requestUpdateDinosaurioDtoToDinosaurio(requestUpdateDinosaurioDto, updateDinosaurio);
		updateDinosaurio = dinosaurioService.update(updateDinosaurio);
		

		ResponseDinosaurioDto response = dinosaurioMapper.dinosaurioToResponseDinosaurioDto(updateDinosaurio);
		response.setIdEspecie(especie.getId());
		response.setIdRecinto(recinto.getId());
		
		return response;
	}

	@Override
	public void delete(RequestDeleteDinosaurioDto requestDeleteDinosaurioDto) throws NotConfirmDeleteDinosaurio, DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		if(!requestDeleteDinosaurioDto.getConfirmacion()) {
			throw new NotConfirmDeleteDinosaurio("No se ha confirmado el borrado del DInosaurio");
		}
		
		Dinosaurio deleteDinosaurio = dinosaurioService.findById(requestDeleteDinosaurioDto.getId());
		if(deleteDinosaurio == null) {
			throw new DinosaurioElementNotFoundException("No exxiste el dinosaurio con ese id");
		}
		dinosaurioService.delete(deleteDinosaurio);
		
	}

}
