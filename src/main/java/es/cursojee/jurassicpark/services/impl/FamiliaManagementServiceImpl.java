package es.cursojee.jurassicpark.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cursojee.jurassicpark.controller.dto.familia.RequestCreateFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.RequestDeleteFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.RequestUpdateFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.ResponseFamiliaDto;
import es.cursojee.jurassicpark.exception.DinosaurioElementNotFoundException;
import es.cursojee.jurassicpark.exception.IntegratedForeignKeyException;
import es.cursojee.jurassicpark.exception.NotConfirmDeleteDinosaurio;
import es.cursojee.jurassicpark.mapper.FamiliaMapper;
import es.cursojee.jurassicpark.model.Especie;
import es.cursojee.jurassicpark.model.Familia;
import es.cursojee.jurassicpark.services.EspecieManagementService;
import es.cursojee.jurassicpark.services.FamiliaManagementService;
import es.cursojee.jurassicpark.services.basic.FamiliaService;

@Transactional
@Service(FamiliaManagementService.BEAN_NAME)
public class FamiliaManagementServiceImpl implements FamiliaManagementService {
	@Autowired
	private FamiliaService familiaService;
	
	@Autowired
	private FamiliaMapper familiaMapper;
	
	@Autowired
	private EspecieManagementService especieService;
	
	@Override
	public List<ResponseFamiliaDto> findAll() {
		// TODO Auto-generated method stub
		List<Familia> listaFamilia = familiaService.findAll();
		return familiaMapper.listFamiliaToListResponseFamiliaDto(listaFamilia);
	}
	
	@Override
	public ResponseFamiliaDto findById(Long id) throws DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		Familia familia = familiaService.findById(id);
		
		if(familia == null) {
			throw new DinosaurioElementNotFoundException("No existe la familia");
		}
		
		return familiaMapper.familiaToResponseFamiliaDto(familia);
	}

	@Override
	public ResponseFamiliaDto create(RequestCreateFamiliaDto requestCreateFamiliaDto) {
		// TODO Auto-generated method stub
		Familia newFamilia = familiaMapper.requestCreateFamiliaDtoToFamilia(requestCreateFamiliaDto);
		newFamilia = familiaService.create(newFamilia);
		return familiaMapper.familiaToResponseFamiliaDto(newFamilia);
	}
	
	@Override
	public ResponseFamiliaDto update(RequestUpdateFamiliaDto requestUpdateFamiliaDto) throws DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		Familia familia = familiaService.findById(requestUpdateFamiliaDto.getId());
		
		if(familia == null) {
			throw new DinosaurioElementNotFoundException("No existe la familia");
		}
		
		familia = familiaMapper.requestUpdateFamiliaDtoToFamilia(requestUpdateFamiliaDto,familia);
		familia = familiaService.update(familia);
		return familiaMapper.familiaToResponseFamiliaDto(familia);
	}

	@Override
	public void delete(RequestDeleteFamiliaDto requestDeleteFamiliaDto) throws NotConfirmDeleteDinosaurio, DinosaurioElementNotFoundException, IntegratedForeignKeyException {
		// TODO Auto-generated method stub
	
		if(!requestDeleteFamiliaDto.getConfirmacion()) {
			throw new NotConfirmDeleteDinosaurio("No se ha confirmado el borrado de la Familia");
		}
		List<Especie> listaEspecies = especieService.findByIdFamilia(requestDeleteFamiliaDto.getId());
		if(!listaEspecies.isEmpty()) {
			throw new IntegratedForeignKeyException("Esta familia tiene especies asociadas y no se puede borrar");
		}
		Familia deleteFamilia = familiaService.findById(requestDeleteFamiliaDto.getId());
		
		if(deleteFamilia == null) {
			throw new DinosaurioElementNotFoundException("No existe la familia");
		}
		familiaService.delete(deleteFamilia);
		
	}

}
