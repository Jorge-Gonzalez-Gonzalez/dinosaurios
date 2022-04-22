package es.cursojee.jurassicpark.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cursojee.jurassicpark.controller.dto.especieTipoAlimentacion.RequestCreateEspecieTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.especieTipoAlimentacion.ResponseEspecieTipoAlimentacionDto;
import es.cursojee.jurassicpark.mapper.EspecieTipoAlimentacionMapper;
import es.cursojee.jurassicpark.model.Especie;
import es.cursojee.jurassicpark.model.EspecieTipoAlimentacion;
import es.cursojee.jurassicpark.model.TipoAlimentacion;
import es.cursojee.jurassicpark.repositories.EspecieTipoAlimentacionRepository;
import es.cursojee.jurassicpark.services.EspecieService;
import es.cursojee.jurassicpark.services.EspecieTipoAlimentacionService;
import es.cursojee.jurassicpark.services.FamiliaService;
import es.cursojee.jurassicpark.services.TipoAlimentacionService;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service(FamiliaService.BEAN_NAME)
@Slf4j
public class EspecieTipoAlimentacionServiceImpl implements EspecieTipoAlimentacionService{

	@Autowired
	private EspecieTipoAlimentacionRepository especieTipoAlimentacionRepository;
	@Autowired
	private EspecieTipoAlimentacionMapper especieTipoAlimentacionMapper;
	@Autowired
	private EspecieService especieService;
	@Autowired
	private TipoAlimentacionService tipoAlimentacionService;
	
	@Override
	public ResponseEspecieTipoAlimentacionDto create(RequestCreateEspecieTipoAlimentacionDto requestCreateEspecieTipoAlimentacionDto) {
		// TODO Auto-generated method stub
		Especie especie = especieService.findById(requestCreateEspecieTipoAlimentacionDto.getIdEspecie());
		if(especie == null) {
			
		}
		TipoAlimentacion tipoAlimentacion = tipoAlimentacionService.findById(requestCreateEspecieTipoAlimentacionDto.getIdTipoAlimentacion());
		if(tipoAlimentacion == null) {
			
		}
		
		EspecieTipoAlimentacion newEspecieTipoAlimentacion = especieTipoAlimentacionMapper.requestCreateEspecieTipoAlimentacionDtoToEspecieTipoAlimentacion(requestCreateEspecieTipoAlimentacionDto);
		newEspecieTipoAlimentacion = especieTipoAlimentacionRepository.save(newEspecieTipoAlimentacion);
		return especieTipoAlimentacionMapper.especieTipoAlimentacionToResponseEspecieTipoAlimentacionDto(newEspecieTipoAlimentacion);
	}

}
