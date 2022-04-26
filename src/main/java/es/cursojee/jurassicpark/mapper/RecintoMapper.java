package es.cursojee.jurassicpark.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import es.cursojee.jurassicpark.controller.dto.recinto.RequestCreateRecintoDto;
import es.cursojee.jurassicpark.controller.dto.recinto.RequestUpdateRecintoDto;
import es.cursojee.jurassicpark.controller.dto.recinto.ResponseRecintoDto;
import es.cursojee.jurassicpark.model.Recinto;

@Mapper(componentModel = "spring")
public interface RecintoMapper {

	Recinto requestCreateRecintoDtoToRecinto(RequestCreateRecintoDto requestCreateRecintoDto);
	Recinto requestUpdateRecintoDtoToRecinto(RequestUpdateRecintoDto requestUpdateRecintoDto,@MappingTarget Recinto recinto);
	RequestUpdateRecintoDto recintoToRequestUpdateRecintoDto(Recinto recintoDto);
	ResponseRecintoDto recintoToResponseRecintoDto(Recinto recinto);
	Recinto responseRecintoDtoToRecinto(ResponseRecintoDto responseRecintoDto);
	
	List<ResponseRecintoDto> listaRecintoToListaResponseRecintoDto(List<Recinto> listaRecinto);
}
