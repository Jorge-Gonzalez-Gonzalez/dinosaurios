package es.cursojee.jurassicpark.services.basic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cursojee.jurassicpark.model.TipoAlimentacion;
import es.cursojee.jurassicpark.repositories.TipoAlimentacionRepository;
import es.cursojee.jurassicpark.services.basic.TipoAlimentacionService;

@Transactional
@Service(TipoAlimentacionService.BEAN_NAME)

public class TipoAlimentacionServiceImpl implements TipoAlimentacionService{
	
	@Autowired
	private TipoAlimentacionRepository tipoAlimentacionRepository;
	@Override
	public List<TipoAlimentacion> findAll() {
		// TODO Auto-generated method stub
		return tipoAlimentacionRepository.findAll();
	}

	@Override
	public TipoAlimentacion findById(Long id) {
		// TODO Auto-generated method stub
		return tipoAlimentacionRepository.findById(id).orElse(null);
	}

	@Override
	public TipoAlimentacion create(TipoAlimentacion tipoAlimentacion) {
		// TODO Auto-generated method stub
		return tipoAlimentacionRepository.save(tipoAlimentacion);
	}

	@Override
	public TipoAlimentacion update(TipoAlimentacion tipoAlimentacion) {
		// TODO Auto-generated method stub
		return tipoAlimentacionRepository.save(tipoAlimentacion);
	}

	@Override
	public void delete(TipoAlimentacion tipoAlimentacion) {
		// TODO Auto-generated method stub
		
		tipoAlimentacionRepository.delete(tipoAlimentacion);
		
	}

}
