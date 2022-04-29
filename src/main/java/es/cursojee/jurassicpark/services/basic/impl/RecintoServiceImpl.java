package es.cursojee.jurassicpark.services.basic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cursojee.jurassicpark.model.Recinto;
import es.cursojee.jurassicpark.repositories.RecintoRepository;
import es.cursojee.jurassicpark.services.basic.RecintoService;

@Transactional
@Service(RecintoService.BEAN_NAME)
public class RecintoServiceImpl implements RecintoService{
	
	@Autowired
	private RecintoRepository recintoRepository;
	
	@Override
	public List<Recinto> findAll() {
		// TODO Auto-generated method stub
		return recintoRepository.findAll();
	}

	@Override
	public Recinto findById(Long id) {
		// TODO Auto-generated method stub
		return recintoRepository.findById(id).orElse(null);
	}
	

	@Override
	public Recinto create(Recinto recinto) {
		// TODO Auto-generated method stub
		return recintoRepository.save(recinto);
	}

	@Override
	public Recinto update(Recinto recinto) {
		// TODO Auto-generated method stub
		return recintoRepository.save(recinto);
	}

	@Override
	public void delete(Recinto recinto) {
		// TODO Auto-generated method stub
		
		recintoRepository.delete(recinto);
		
	}

}
