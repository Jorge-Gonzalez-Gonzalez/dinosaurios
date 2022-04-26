package es.cursojee.jurassicpark.services.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cursojee.jurassicpark.controller.dto.especie.RequestCreateEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.RequestDeleteEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.RequestUpdateEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.ResponseEspecieDto;
import es.cursojee.jurassicpark.exception.DinosaurioElementNotFoundException;
import es.cursojee.jurassicpark.exception.NotConfirmDeleteDinosaurio;
import es.cursojee.jurassicpark.mapper.EspecieMapper;
import es.cursojee.jurassicpark.model.Especie;
import es.cursojee.jurassicpark.model.Familia;
import es.cursojee.jurassicpark.repositories.EspecieRepository;
import es.cursojee.jurassicpark.services.EspecieManagementService;
import es.cursojee.jurassicpark.services.FamiliaManagementService;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service(EspecieManagementService.BEAN_NAME)
@Slf4j
public class EspecieManagementServiceImpl implements EspecieManagementService {

	@Autowired
	private EspecieRepository especieRepository;
	
	@Autowired
	private EspecieMapper especieMapper;

	@Override
	public List<ResponseEspecieDto> findAll() {
		// TODO Auto-generated method stub
		List<Especie> listaEspecie = especieRepository.findAll();
		return especieMapper.listEspecieToListResponseEspecieDto(listaEspecie);
	}

	@Override
	public ResponseEspecieDto findEspecieById(Long id) throws DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		Especie especie = findById(id);
		return especieMapper.especieToResponseEspecieDto(especie);
	}
	
	@Override
	public ResponseEspecieDto create(RequestCreateEspecieDto requestCreateEspecieDto)
			throws DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		//Familia familia = familiaService.findById(requestCreateEspecieDto.getIdFamilia());
		Especie newEspecie = especieMapper.requestCreateEspecieDtoToEspecie(requestCreateEspecieDto);
		newEspecie = especieRepository.save(newEspecie);
		return especieMapper.especieToResponseEspecieDto(newEspecie);
	}

	@Override
	public ResponseEspecieDto update(RequestUpdateEspecieDto requestUpdateEspecieDto) throws DinosaurioElementNotFoundException {
		// TODO Auto-generated method stub
		Especie especie = findById(requestUpdateEspecieDto.getId());
		especie = especieMapper.requestUpdateEspecieDtoToEspecie(requestUpdateEspecieDto, especie);
		especie = especieRepository.save(especie);

		return especieMapper.especieToResponseEspecieDto(especie);
	}

	@Override
	public void delete(RequestDeleteEspecieDto requestDeleteEspecieDto) throws DinosaurioElementNotFoundException, NotConfirmDeleteDinosaurio {
		// TODO Auto-generated method stub
		if (!requestDeleteEspecieDto.getConfirmacion()) {
			throw new NotConfirmDeleteDinosaurio("No se ha confirmado el borrado de la especie");
		}
		Especie deleteEspecie = findById(requestDeleteEspecieDto.getId());
		especieRepository.delete(deleteEspecie);

	}


	@Override
	public Especie findById(Long id) throws DinosaurioElementNotFoundException{
		// TODO Auto-generated method stub
		Especie especie = especieRepository.findById(id).orElse(null);
		System.out.println(especie);
		if (especie == null) {
			throw new DinosaurioElementNotFoundException("No se ha encontrado el dinosaurio con el id estableciido");
		}
		return especie;
	}

	@Override
	public List<Especie> findByIdFamilia(Long id) {
		// TODO Auto-generated method stub
		List<Especie> listaEspecies = especieRepository.findByFamilia(id);
		return listaEspecies;
	}

}
