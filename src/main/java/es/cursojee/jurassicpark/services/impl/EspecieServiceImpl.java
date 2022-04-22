package es.cursojee.jurassicpark.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cursojee.jurassicpark.controller.dto.especie.RequestCreateEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.RequestDeleteEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.ResponseEspecieDto;
import es.cursojee.jurassicpark.mapper.EspecieMapper;
import es.cursojee.jurassicpark.model.Especie;
import es.cursojee.jurassicpark.model.Familia;
import es.cursojee.jurassicpark.repositories.EspecieRepository;
import es.cursojee.jurassicpark.services.EspecieService;
import es.cursojee.jurassicpark.services.FamiliaService;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service(EspecieService.BEAN_NAME)
@Slf4j
public class EspecieServiceImpl implements EspecieService{
	
	@Autowired
	private EspecieRepository especieRepository;
	@Autowired
	private FamiliaService familiaService;
	@Autowired
	private EspecieMapper especieMapper;
	
	@Override
	public Especie findById(Long id) {
		// TODO Auto-generated method stub
		return especieRepository.getById(id);
	}

	@Override
	public ResponseEspecieDto create(RequestCreateEspecieDto requestCreateEspecieDto) {
		// TODO Auto-generated method stub
		Familia familia = familiaService.findById(requestCreateEspecieDto.getId_familia());
		if(familia == null) {
			
		}
		Especie newEspecie = especieMapper.requestCreateEspecieDtoToEspecie(requestCreateEspecieDto);
		newEspecie = especieRepository.save(newEspecie);
		return especieMapper.especieToResponseEspecieDto(newEspecie);
	}

	@Override
	public void delete(RequestDeleteEspecieDto requestDeleteEspecieDto) {
		// TODO Auto-generated method stub
		if(!requestDeleteEspecieDto.getConfirmacion()) {
			
		}
		Especie deleteEspecie = findById(requestDeleteEspecieDto.getId());
		especieRepository.delete(deleteEspecie);
		
	}

}
