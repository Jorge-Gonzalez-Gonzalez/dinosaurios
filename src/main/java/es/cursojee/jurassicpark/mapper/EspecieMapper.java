package es.cursojee.jurassicpark.mapper;

import org.mapstruct.Mapper;

import es.cursojee.jurassicpark.controller.dto.especie.RequestCreateEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.ResponseEspecieDto;
import es.cursojee.jurassicpark.model.Especie;

@Mapper(componentModel = "spring")
public interface EspecieMapper {

	Especie requestCreateEspecieDtoToEspecie(RequestCreateEspecieDto requestCreateEspecieDto);
	ResponseEspecieDto especieToResponseEspecieDto(Especie especie);
}
