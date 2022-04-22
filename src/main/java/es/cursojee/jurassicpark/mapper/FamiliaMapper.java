package es.cursojee.jurassicpark.mapper;

import org.mapstruct.Mapper;

import es.cursojee.jurassicpark.controller.dto.familia.RequestCreateFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.ResponseFamiliaDto;
import es.cursojee.jurassicpark.model.Familia;

@Mapper(componentModel = "spring")
public interface FamiliaMapper {
	
	Familia requestCreateFamiliaDtoToFamilia(RequestCreateFamiliaDto requestCreateFamiliaDto);
	ResponseFamiliaDto familiaToResponseFamiliaDto(Familia familia);
}
