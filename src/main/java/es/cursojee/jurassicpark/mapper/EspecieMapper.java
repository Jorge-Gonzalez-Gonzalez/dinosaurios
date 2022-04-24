package es.cursojee.jurassicpark.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import es.cursojee.jurassicpark.controller.dto.especie.RequestCreateEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.RequestUpdateEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.ResponseEspecieDto;
import es.cursojee.jurassicpark.model.Especie;

@Mapper(componentModel = "spring")
public interface EspecieMapper {

	Especie requestCreateEspecieDtoToEspecie(RequestCreateEspecieDto requestCreateEspecieDto);
	Especie requestUpdateEspecieDtoToEspecie(RequestUpdateEspecieDto requestUpdateEspecieDto,@MappingTarget Especie especie);
	ResponseEspecieDto especieToResponseEspecieDto(Especie especie);
	List<ResponseEspecieDto> listEspecieToListResponseEspecieDto(List<Especie> listaEspecie);
	
}
