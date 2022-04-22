package es.cursojee.jurassicpark.mapper;

import es.cursojee.jurassicpark.controller.dto.especie.RequestCreateEspecieDto;
import es.cursojee.jurassicpark.controller.dto.especie.ResponseEspecieDto;
import es.cursojee.jurassicpark.model.Especie;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-22T11:02:03+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class EspecieMapperImpl implements EspecieMapper {

    @Override
    public Especie requestCreateEspecieDtoToEspecie(RequestCreateEspecieDto requestCreateEspecieDto) {
        if ( requestCreateEspecieDto == null ) {
            return null;
        }

        Especie especie = new Especie();

        especie.setId( requestCreateEspecieDto.getId() );
        especie.setNombre( requestCreateEspecieDto.getNombre() );
        especie.setCodigoTipoPeligrosidad( requestCreateEspecieDto.getCodigoTipoPeligrosidad() );
        especie.setLongitud( requestCreateEspecieDto.getLongitud() );

        return especie;
    }

    @Override
    public ResponseEspecieDto especieToResponseEspecieDto(Especie especie) {
        if ( especie == null ) {
            return null;
        }

        ResponseEspecieDto responseEspecieDto = new ResponseEspecieDto();

        responseEspecieDto.setNombre( especie.getNombre() );
        responseEspecieDto.setCodigoTipoPeligrosidad( especie.getCodigoTipoPeligrosidad() );
        responseEspecieDto.setLongitud( especie.getLongitud() );

        return responseEspecieDto;
    }
}
