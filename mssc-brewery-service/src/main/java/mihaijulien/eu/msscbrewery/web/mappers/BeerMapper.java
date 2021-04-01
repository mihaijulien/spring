package mihaijulien.eu.msscbrewery.web.mappers;

import mihaijulien.eu.msscbrewery.domain.Beer;
import mihaijulien.eu.msscbrewery.web.model.v2.BeerDTOv2;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDTOv2 BeerToBeerDTO(Beer beer);

    Beer BeerDTOtoBeer(BeerDTOv2 beerDTOv2);
}
