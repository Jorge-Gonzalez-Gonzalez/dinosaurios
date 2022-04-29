package es.cursojee.jurassicpark.services.basic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cursojee.jurassicpark.model.Familia;
import es.cursojee.jurassicpark.repositories.FamiliaRepository;
import es.cursojee.jurassicpark.services.basic.FamiliaService;

@Transactional
@Service(FamiliaService.BEAN_NAME)
public class FamiliaServiceImpl implements FamiliaService{
	
	@Autowired
	private FamiliaRepository familiaRepository;
	@Override
	public List<Familia> findAll() {
		// TODO Auto-generated method stub
		return familiaRepository.findAll();
	}

	@Override
	public Familia findById(Long id) {
		// TODO Auto-generated method stub
		return familiaRepository.findById(id).orElse(null);
	}

	@Override
	public Familia create(Familia familia) {
		// TODO Auto-generated method stub
		return familiaRepository.save(familia);
	}

	@Override
	public Familia update(Familia familia) {
		// TODO Auto-generated method stub
		return familiaRepository.save(familia);
	}

	@Override
	public void delete(Familia familia) {
		// TODO Auto-generated method stub
		
		familiaRepository.delete(familia);
		
	}

}
