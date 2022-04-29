package es.cursojee.jurassicpark.services.basic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cursojee.jurassicpark.model.EspecieTipoAlimentacion;
import es.cursojee.jurassicpark.repositories.EspecieTipoAlimentacionRepository;
import es.cursojee.jurassicpark.services.basic.EspecieTipoAlimentacionService;


@Transactional
@Service(EspecieTipoAlimentacionService.BEAN_NAME)

public class EspecieTipoAlimentacionServiceImpl implements EspecieTipoAlimentacionService {
	
	@Autowired
	private EspecieTipoAlimentacionRepository especieTipoAlimentacionRepository;
	@Override
	public List<EspecieTipoAlimentacion> findAll() {
		// TODO Auto-generated method stub
		return especieTipoAlimentacionRepository.findAll();
	}

	@Override
	public EspecieTipoAlimentacion findById(Long id) {
		// TODO Auto-generated method stub
		return especieTipoAlimentacionRepository.findById(id).orElse(null);
	}

	@Override
	public EspecieTipoAlimentacion create(EspecieTipoAlimentacion especieTipoAlimentacion) {
		// TODO Auto-generated method stub
		return especieTipoAlimentacionRepository.save(especieTipoAlimentacion);
	}

	@Override
	public EspecieTipoAlimentacion update(EspecieTipoAlimentacion especieTipoAlimentacion) {
		// TODO Auto-generated method stub
		return especieTipoAlimentacionRepository.save(especieTipoAlimentacion);
	}

	@Override
	public void delete(EspecieTipoAlimentacion especieTipoAlimentacion) {
		// TODO Auto-generated method stub
		especieTipoAlimentacionRepository.delete(especieTipoAlimentacion);
		
	}

	@Override
	public EspecieTipoAlimentacion findByIdEspecie(Long id) {
		// TODO Auto-generated method stub
		return especieTipoAlimentacionRepository.findByIdEspecie(id);
	}

	@Override
	public List<EspecieTipoAlimentacion> findByIdAlimentacion(Long id) {
		// TODO Auto-generated method stub
		List<EspecieTipoAlimentacion> listaEspecieTipoAlimentacion = especieTipoAlimentacionRepository.findByIdTipoAlimentacion(id);
		return listaEspecieTipoAlimentacion;
	}

}
