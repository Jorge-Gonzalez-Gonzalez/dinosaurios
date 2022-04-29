package es.cursojee.jurassicpark.services.basic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cursojee.jurassicpark.model.Especie;
import es.cursojee.jurassicpark.repositories.EspecieRepository;

import es.cursojee.jurassicpark.services.basic.EspecieService;

@Transactional
@Service(EspecieService.BEAN_NAME)

public class EspecieServiceImpl implements EspecieService {
	
	@Autowired
	private EspecieRepository especieRepository;
	@Override
	public List<Especie> findAll() {
		// TODO Auto-generated method stub
		return especieRepository.findAll();
	}

	@Override
	public Especie findById(Long id) {
		// TODO Auto-generated method stub
		return especieRepository.findById(id).orElse(null);
	}

	@Override
	public Especie create(Especie especie) {
		// TODO Auto-generated method stub
		return especieRepository.save(especie);
	}

	@Override
	public Especie update(Especie especie) {
		// TODO Auto-generated method stub
		return especieRepository.save(especie);
	}

	@Override
	public void delete(Especie especie) {
		// TODO Auto-generated method stub
		
		especieRepository.delete(especie);
		
	}
	
	@Override
	public List<Especie> findByIdFamilia(Long id) {
		return especieRepository.findByFamilia(id);
	}

}
