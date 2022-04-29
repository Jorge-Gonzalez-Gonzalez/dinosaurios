package es.cursojee.jurassicpark.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cursojee.jurassicpark.controller.dto.recinto.RequestCreateRecintoDto;
import es.cursojee.jurassicpark.controller.dto.recinto.RequestDeleteRecintoDto;
import es.cursojee.jurassicpark.controller.dto.recinto.RequestUpdateRecintoDto;
import es.cursojee.jurassicpark.controller.dto.recinto.ResponseRecintoDto;
import es.cursojee.jurassicpark.exception.IntegratedForeignKeyException;
import es.cursojee.jurassicpark.exception.NotConfirmRecintoDelete;
import es.cursojee.jurassicpark.exception.RecintoNotFoundException;
import es.cursojee.jurassicpark.mapper.RecintoMapper;
import es.cursojee.jurassicpark.model.Dinosaurio;
import es.cursojee.jurassicpark.model.Recinto;
import es.cursojee.jurassicpark.services.RecintoManagementService;
import es.cursojee.jurassicpark.services.basic.DinosaurioService;
import es.cursojee.jurassicpark.services.basic.RecintoService;

@Transactional
@Service(RecintoManagementService.BEAN_NAME)
public class RecintoManagementServiceImpl implements RecintoManagementService{
	
	@Autowired
	private RecintoService recintoService;
	
	@Autowired
	private RecintoMapper recintoMapper;
	
	@Autowired 
	private DinosaurioService dinosaurioService;
	
	@Override
	public List<ResponseRecintoDto> findAll() {
		// TODO Auto-generated method stub
		List<Recinto> listaRecintos = recintoService.findAll();
		return recintoMapper.listaRecintoToListaResponseRecintoDto(listaRecintos);
	}

	@Override
	public ResponseRecintoDto findById(Long id) throws RecintoNotFoundException {
		// TODO Auto-generated method stub
		Recinto recinto = recintoService.findById(id);
		if(recinto == null) {
			throw new RecintoNotFoundException("No se ha encontrado el recinto con ese id");
		}
		return recintoMapper.recintoToResponseRecintoDto(recinto);
	}

	@Override
	public ResponseRecintoDto create(RequestCreateRecintoDto requestCreateRecintoDto) {
		// TODO Auto-generated method stub
		Recinto newRecinto = recintoMapper.requestCreateRecintoDtoToRecinto(requestCreateRecintoDto);
		newRecinto = recintoService.create(newRecinto);
		
		return  recintoMapper.recintoToResponseRecintoDto(newRecinto);
	}

	@Override
	public ResponseRecintoDto update(RequestUpdateRecintoDto requestUpdateRecintoDto) throws RecintoNotFoundException {
		// TODO Auto-generated method stub
		Recinto updateRecinto = recintoService.findById(requestUpdateRecintoDto.getId());
		
		if(updateRecinto == null) {
			throw new RecintoNotFoundException("No existe el recinto");
		}
		
		updateRecinto = recintoMapper.requestUpdateRecintoDtoToRecinto(requestUpdateRecintoDto, updateRecinto);
		updateRecinto = recintoService.update(updateRecinto);
		
		return recintoMapper.recintoToResponseRecintoDto(updateRecinto);
	}

	@Override
	public void delete(RequestDeleteRecintoDto requestDeleteRecintoDto) throws NotConfirmRecintoDelete, IntegratedForeignKeyException, RecintoNotFoundException {
		// TODO Auto-generated method stub
		if(!requestDeleteRecintoDto.getConfirmacion()) {
			throw new NotConfirmRecintoDelete("No se ha confirmado el borrado del Recinto");
		}
		System.out.println(requestDeleteRecintoDto.getId());
		List<Dinosaurio> listaDinosaurios = dinosaurioService.findByRecinto(requestDeleteRecintoDto.getId());
		
		System.out.println(listaDinosaurios);
		if(!listaDinosaurios.isEmpty()) {
			throw new IntegratedForeignKeyException("Este recinto tiene dinosaurios asociadas y no se puede borrar");
		}
		
		Recinto deleteRecinto = recintoService.findById(requestDeleteRecintoDto.getId());
		
		if(deleteRecinto == null) {
			throw new RecintoNotFoundException("No se ha encontrado el recinto con ese id");
		}
		
		recintoService.delete(deleteRecinto);
	}

}
