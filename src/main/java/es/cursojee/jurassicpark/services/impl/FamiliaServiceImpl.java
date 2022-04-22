package es.cursojee.jurassicpark.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cursojee.jurassicpark.controller.dto.familia.RequestCreateFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.RequestDeleteFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.ResponseFamiliaDto;
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
	public Familia findById(Long id) {
		// TODO Auto-generated method stub
		return familiaRepository.getById(id);
	}

	@Override
	public ResponseFamiliaDto create(RequestCreateFamiliaDto requestCreateFamiliaDto) {
		// TODO Auto-generated method stub
		Familia newFamilia = familiaMapper.requestCreateFamiliaDtoToFamilia(requestCreateFamiliaDto);
		newFamilia = familiaRepository.save(newFamilia);
		return familiaMapper.familiaToResponseFamiliaDto(newFamilia);
	}

	@Override
	public void delete(RequestDeleteFamiliaDto requestDeleteFamiliaDto) {
		// TODO Auto-generated method stub
		
		if(!requestDeleteFamiliaDto.getConfirmacion()) {
			
		}
		Familia deleteFamilia = findById(requestDeleteFamiliaDto.getId());
		familiaRepository.delete(deleteFamilia);
		
	}

}
