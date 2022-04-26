package es.cursojee.jurassicpark.services.basic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cursojee.jurassicpark.model.Dinosaurio;
import es.cursojee.jurassicpark.repositories.DinosaurioRepository;
import es.cursojee.jurassicpark.services.EspecieManagementService;
import es.cursojee.jurassicpark.services.basic.DinosaurioService;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service(EspecieManagementService.BEAN_NAME)
@Slf4j
public class DinosaurioServiceImpl implements DinosaurioService {

	@Autowired
	private DinosaurioRepository dinosaurioRepository;
	
	@Override
	public List<Dinosaurio> findAll() {
		// TODO Auto-generated method stub
		return dinosaurioRepository.findAll();
	}

	@Override
	public Dinosaurio findById(Long id) {
		// TODO Auto-generated method stub
		return dinosaurioRepository.findById(id).orElse(null);
	}

	@Override
	public Dinosaurio create(Dinosaurio dinosaurio) {
		// TODO Auto-generated method stub
		return dinosaurioRepository.save(dinosaurio);
	}

	@Override
	public Dinosaurio update(Dinosaurio dinosaurio) {
		// TODO Auto-generated method stub
		return dinosaurioRepository.save(dinosaurio);
	}

	@Override
	public void delete(Dinosaurio dinosaurio) {
		// TODO Auto-generated method st
		dinosaurioRepository.delete(dinosaurio);
		
	}

	@Override
	public List<Dinosaurio> findByRecinto(Long id) {
		// TODO Auto-generated method stub
		return dinosaurioRepository.findByRecinto(id);
	}

}
