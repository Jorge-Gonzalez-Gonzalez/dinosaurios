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
import es.cursojee.jurassicpark.exception.NotConfirmDeleteDinosaurio;
import es.cursojee.jurassicpark.mapper.FamiliaMapper;
import es.cursojee.jurassicpark.model.Familia;
import es.cursojee.jurassicpark.repositories.FamiliaRepository;
import es.cursojee.jurassicpark.services.FamiliaService;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Service(FamiliaService.BEAN_NAME)
@Slf4j
public class FamiliaServiceImpl implements FamiliaService {
	@Autowired
	private FamiliaRepository familiaRepository;
	
	@Autowired
	private FamiliaMapper familiaMapper;
	
	@Override
	public List<ResponseFamiliaDto> findAll() {
		// TODO Auto-generated method stub
		List<Familia> listaFamilia = familiaRepository.findAll();
		return familiaMapper.listFamiliaToListResponseFamiliaDto(listaFamilia);
	}
	
	@Override
	public ResponseFamiliaDto findFamiliaById(Long id) throws DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		Familia familia = findById(id);
		return familiaMapper.familiaToResponseFamiliaDto(familia);
	}

	@Override
	public ResponseFamiliaDto create(RequestCreateFamiliaDto requestCreateFamiliaDto) {
		// TODO Auto-generated method stub
		Familia newFamilia = familiaMapper.requestCreateFamiliaDtoToFamilia(requestCreateFamiliaDto);
		newFamilia = familiaRepository.save(newFamilia);
		return familiaMapper.familiaToResponseFamiliaDto(newFamilia);
	}
	
	@Override
	public ResponseFamiliaDto update(RequestUpdateFamiliaDto requestUpdateFamiliaDto) throws DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		Familia familia = findById(requestUpdateFamiliaDto.getId());
		familia = familiaMapper.requestUpdateFamiliaDtoToFamilia(requestUpdateFamiliaDto,familia);
		familia = familiaRepository.save(familia);
		return familiaMapper.familiaToResponseFamiliaDto(familia);
	}

	@Override
	public void delete(RequestDeleteFamiliaDto requestDeleteFamiliaDto) throws NotConfirmDeleteDinosaurio, DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		
		if(!requestDeleteFamiliaDto.getConfirmacion()) {
			throw new NotConfirmDeleteDinosaurio("No se ha confirmado el borrado de la Familia");
		}
		Familia deleteFamilia = findById(requestDeleteFamiliaDto.getId());
		familiaRepository.delete(deleteFamilia);
		
	}
	
	public Familia findById(Long id) throws DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		Familia familia = familiaRepository.getById(id);
		if(familia != null) {
			throw new DinosaurioElementNotFoundException("No existe el dinosaurio con ese id");
		}
		return familiaRepository.getById(id);
	}

}
