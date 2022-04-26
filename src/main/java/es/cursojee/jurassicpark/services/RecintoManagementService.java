package es.cursojee.jurassicpark.services;

import java.util.List;

import es.cursojee.jurassicpark.controller.dto.recinto.RequestCreateRecintoDto;
import es.cursojee.jurassicpark.controller.dto.recinto.RequestDeleteRecintoDto;
import es.cursojee.jurassicpark.controller.dto.recinto.RequestUpdateRecintoDto;
import es.cursojee.jurassicpark.controller.dto.recinto.ResponseRecintoDto;
import es.cursojee.jurassicpark.exception.IntegratedForeignKeyException;
import es.cursojee.jurassicpark.exception.NotConfirmRecintoDelete;
import es.cursojee.jurassicpark.exception.RecintoNotFoundException;

public interface RecintoManagementService {

	static final String BEAN_NAME ="recintoManagementService";
	
	List<ResponseRecintoDto> findAll();
	ResponseRecintoDto findById(Long id) throws RecintoNotFoundException;
	ResponseRecintoDto create(RequestCreateRecintoDto requestCreateRecintoDto);
	ResponseRecintoDto update(RequestUpdateRecintoDto requestUpdateRecintoDto);
	void delete(RequestDeleteRecintoDto requestDeleteRecintoDto) throws NotConfirmRecintoDelete, IntegratedForeignKeyException;
}
