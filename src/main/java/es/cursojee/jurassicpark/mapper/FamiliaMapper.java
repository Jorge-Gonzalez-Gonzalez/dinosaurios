package es.cursojee.jurassicpark.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import es.cursojee.jurassicpark.controller.dto.familia.RequestCreateFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.RequestUpdateFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.ResponseFamiliaDto;
import es.cursojee.jurassicpark.model.Familia;

@Mapper(componentModel = "spring")
public interface FamiliaMapper {
	
	Familia requestCreateFamiliaDtoToFamilia(RequestCreateFamiliaDto requestCreateFamiliaDto);
	Familia requestUpdateFamiliaDtoToFamilia(RequestUpdateFamiliaDto requestUpdateFamiliaDto,@MappingTarget Familia familia);
	ResponseFamiliaDto familiaToResponseFamiliaDto(Familia familia);
	List<ResponseFamiliaDto> listFamiliaToListResponseFamiliaDto(List<Familia> listaFamilia);
}
