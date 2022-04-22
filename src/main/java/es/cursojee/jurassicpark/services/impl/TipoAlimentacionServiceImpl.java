package es.cursojee.jurassicpark.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cursojee.jurassicpark.model.TipoAlimentacion;
import es.cursojee.jurassicpark.repositories.TipoAlimentacionRepository;
import es.cursojee.jurassicpark.services.FamiliaService;
import es.cursojee.jurassicpark.services.TipoAlimentacionService;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service(FamiliaService.BEAN_NAME)
@Slf4j
public class TipoAlimentacionServiceImpl implements TipoAlimentacionService{
	
	@Autowired
	private TipoAlimentacionRepository tipoAlimentacionRepository;
	@Override
	public TipoAlimentacion findById(Long id) {
		// TODO Auto-generated method stub
		return tipoAlimentacionRepository.getById(id);
	}

}
