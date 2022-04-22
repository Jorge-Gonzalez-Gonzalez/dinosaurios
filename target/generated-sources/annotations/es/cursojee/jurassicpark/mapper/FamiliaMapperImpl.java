package es.cursojee.jurassicpark.mapper;

import es.cursojee.jurassicpark.controller.dto.familia.RequestCreateFamiliaDto;
import es.cursojee.jurassicpark.controller.dto.familia.ResponseFamiliaDto;
import es.cursojee.jurassicpark.model.Familia;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-22T11:02:03+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class FamiliaMapperImpl implements FamiliaMapper {

    @Override
    public Familia requestCreateFamiliaDtoToFamilia(RequestCreateFamiliaDto requestCreateFamiliaDto) {
        if ( requestCreateFamiliaDto == null ) {
            return null;
        }

        Familia familia = new Familia();

        familia.setId( requestCreateFamiliaDto.getId() );
        familia.setNombre( requestCreateFamiliaDto.getNombre() );

        return familia;
    }

    @Override
    public ResponseFamiliaDto familiaToResponseFamiliaDto(Familia familia) {
        if ( familia == null ) {
            return null;
        }

        ResponseFamiliaDto responseFamiliaDto = new ResponseFamiliaDto();

        responseFamiliaDto.setNombre( familia.getNombre() );

        return responseFamiliaDto;
    }
}
