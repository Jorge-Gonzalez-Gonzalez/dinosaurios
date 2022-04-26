package es.cursojee.jurassicpark.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import es.cursojee.jurassicpark.controller.dto.dinosaurio.RequestCreateDinosaurioDto;
import es.cursojee.jurassicpark.controller.dto.dinosaurio.RequestUpdateDinosaurioDto;
import es.cursojee.jurassicpark.controller.dto.dinosaurio.ResponseDinosaurioDto;
import es.cursojee.jurassicpark.model.Dinosaurio;

@Mapper(componentModel = "spring")
public interface DinosaurioMapper {

	Dinosaurio requestCreatetDinosaurioDtoToDinosaurio(RequestCreateDinosaurioDto requestCreateDinosaurioDto);
	Dinosaurio requestUpdateDinosaurioDtoToDinosaurio(RequestUpdateDinosaurioDto requestUpdateDinosaurioDto,@MappingTarget Dinosaurio dinosaurio);
	ResponseDinosaurioDto dinosaurioToResponseDinosaurioDto(Dinosaurio dinosaurio);
	List<ResponseDinosaurioDto> listaDinosaurioToListaResponseDinosaurioDto(List<Dinosaurio> listaDinosaurio);
}

