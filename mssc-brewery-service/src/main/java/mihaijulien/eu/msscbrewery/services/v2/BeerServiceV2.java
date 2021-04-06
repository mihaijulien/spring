package mihaijulien.eu.msscbrewery.services.v2;

import mihaijulien.eu.msscbrewery.web.model.v2.BeerDTOv2;
import mihaijulien.eu.msscbrewery.web.model.v2.BeerPagedList;
import mihaijulien.eu.msscbrewery.web.model.v2.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerServiceV2 {
    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest);

    BeerDTOv2 getBeerById(UUID beerId);

    BeerDTOv2 updateBeer(UUID beerID, BeerDTOv2 beerDTOv2);

    void deleteById(UUID beerId);

    BeerDTOv2 saveNewBeer(BeerDTOv2 beerDTOv2);
}
