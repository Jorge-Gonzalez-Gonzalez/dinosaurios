package es.cursojee.jurassicpark.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cursojee.jurassicpark.controller.dto.especie.RequestCreateEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.RequestDeleteEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.RequestUpdateEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.ResponseEspecieDto;
import es.cursojee.jurassicpark.exception.DinosaurioElementNotFoundException;
import es.cursojee.jurassicpark.exception.IntegratedForeignKeyException;
import es.cursojee.jurassicpark.exception.NotConfirmDeleteDinosaurio;
import es.cursojee.jurassicpark.mapper.EspecieMapper;
import es.cursojee.jurassicpark.model.Especie;
import es.cursojee.jurassicpark.model.EspecieTipoAlimentacion;
import es.cursojee.jurassicpark.model.Familia;
import es.cursojee.jurassicpark.services.EspecieManagementService;
import es.cursojee.jurassicpark.services.EspecieTipoAlimentacionManagementService;
import es.cursojee.jurassicpark.services.basic.EspecieService;
import es.cursojee.jurassicpark.services.basic.FamiliaService;

@Transactional
@Service(EspecieManagementService.BEAN_NAME)
public class EspecieManagementServiceImpl implements EspecieManagementService {

	@Autowired
	private EspecieService especieService;
	
	@Autowired
	private EspecieMapper especieMapper;
	
	@Autowired
	private FamiliaService familiaService;
	
	@Autowired
	private EspecieTipoAlimentacionManagementService especieTipoAlimentacionManagementService;

	@Override
	public List<ResponseEspecieDto> findAll() {
		// TODO Auto-generated method stub
		List<Especie> listaEspecie = especieService.findAll();
		return especieMapper.listEspecieToListResponseEspecieDto(listaEspecie);
	}

	@Override
	public ResponseEspecieDto findById(Long id) throws DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		
		Especie especie = especieService.findById(id);
		
		if(especie == null) {
			throw new DinosaurioElementNotFoundException("No existe la especie con ese id");
		}
		
		ResponseEspecieDto response = especieMapper.especieToResponseEspecieDto(especie);
		response.setIdFamilia(especie.getFamilia().getId());
		return response;
	}
	
	@Override
	public ResponseEspecieDto create(RequestCreateEspecieDto requestCreateEspecieDto)throws DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		Familia familia = familiaService.findById(requestCreateEspecieDto.getIdFamilia());
		
		if(familia == null) {
			throw new DinosaurioElementNotFoundException("No existe la familia con ese id");
		}
		
		Especie newEspecie = especieMapper.requestCreateEspecieDtoToEspecie(requestCreateEspecieDto);
		newEspecie = especieService.create(newEspecie);
		
		ResponseEspecieDto response = especieMapper.especieToResponseEspecieDto(newEspecie);
		response.setIdFamilia(familia.getId());
		return response;
	}

	@Override
	public ResponseEspecieDto update(RequestUpdateEspecieDto requestUpdateEspecieDto) throws DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		Familia familia = familiaService.findById(requestUpdateEspecieDto.getIdFamilia());
		Especie especie = especieService.findById(requestUpdateEspecieDto.getId());
		
		if(especie == null) {
			throw new DinosaurioElementNotFoundException("No existe la especie con ese id");
		}
		
		if(familia == null) {
			throw new DinosaurioElementNotFoundException("No existe la familia con ese id");
		}
		especie = especieMapper.requestUpdateEspecieDtoToEspecie(requestUpdateEspecieDto, especie);
		especie = especieService.update(especie);


		ResponseEspecieDto response = especieMapper.especieToResponseEspecieDto(especie);
		response.setIdFamilia(familia.getId());
		return response;
	}

	@Override
	public void delete(RequestDeleteEspecieDto requestDeleteEspecieDto) throws DinosaurioElementNotFoundException, NotConfirmDeleteDinosaurio, IntegratedForeignKeyException {
		// TODO Auto-generated method stub
		
		if (!requestDeleteEspecieDto.getConfirmacion()) {
			throw new NotConfirmDeleteDinosaurio("No se ha confirmado el borrado de la especie");
		}
	
		EspecieTipoAlimentacion especieTipoAlimentacion = especieTipoAlimentacionManagementService.findByIdEspecie(requestDeleteEspecieDto.getId());
		if(especieTipoAlimentacion != null) {
			throw new IntegratedForeignKeyException("Esta familia tiene especies asociadas y no se puede borrar");
		}
		Especie deleteEspecie = especieService.findById(requestDeleteEspecieDto.getId());

		if(deleteEspecie == null) {
			throw new DinosaurioElementNotFoundException("No existe la especie con ese id");
		}
		especieService.delete(deleteEspecie);

	}

	@Override
	public List<Especie> findByIdFamilia(Long id) {
		// TODO Auto-generated method stub
		List<Especie> listaEspecies = especieService.findByIdFamilia(id);
		return listaEspecies;
	}

}
