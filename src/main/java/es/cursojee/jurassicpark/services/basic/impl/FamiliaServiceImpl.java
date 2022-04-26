package es.cursojee.jurassicpark.services.basic.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cursojee.jurassicpark.model.Familia;
import es.cursojee.jurassicpark.services.EspecieManagementService;
import es.cursojee.jurassicpark.services.basic.FamiliaService;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service(EspecieManagementService.BEAN_NAME)
@Slf4j
public class FamiliaServiceImpl implements FamiliaService{
	
	@Override
	public List<Familia> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Familia findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Familia create(Familia familia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Familia update(Familia familia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Familia familia) {
		// TODO Auto-generated method stub
		
	}

}
