package es.cursojee.jurassicpark.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import es.cursojee.jurassicpark.controller.dto.tipoAlimentacion.RequestCreateTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.tipoAlimentacion.RequestUpdateTipoAlimentacionDto;
import es.cursojee.jurassicpark.controller.dto.tipoAlimentacion.ResponseTipoAlimentacionDto;
import es.cursojee.jurassicpark.model.TipoAlimentacion;

@Mapper(componentModel = "spring")
public interface TipoAlimentacionMapper {
	
	TipoAlimentacion requestCreateTipoAlimentacionDtoToTipoAlimentacion(RequestCreateTipoAlimentacionDto requestCreateTipoAlimentacionDto);
	TipoAlimentacion RequestUpdateTipoAlimentacionDto(RequestUpdateTipoAlimentacionDto requestUpdateTipoAlimentacionDto,@MappingTarget TipoAlimentacion tipoAlimentacion);
	ResponseTipoAlimentacionDto tipoAlimentacionToResponseTipoAlimentacion(TipoAlimentacion tipoAlimentacion);
	List<ResponseTipoAlimentacionDto> listTipoAlimentacionToListResponseTipoAlimentacionDto(List<TipoAlimentacion> listaTipoAlimentacion);
	
}

