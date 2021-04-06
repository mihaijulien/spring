package mihaijulien.eu.msscbrewery.web.mappers;

import mihaijulien.eu.msscbrewery.domain.Beer;
import mihaijulien.eu.msscbrewery.web.model.v2.BeerDTOv2;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    BeerDTOv2 beerToBeerDTO(Beer beer);

    Beer beerDTOtoBeer(BeerDTOv2 beerDTOv2);
}
