package mihaijulien.eu.msscbrewery.services.v2;

import mihaijulien.eu.msscbrewery.web.model.v2.BeerDTOv2;

import java.util.UUID;

public interface BeerServiceV2 {
    BeerDTOv2 getBeerById(UUID beerId);

    BeerDTOv2 updateBeer(UUID beerID, BeerDTOv2 beerDTOv2);

    void deleteById(UUID beerId);

    BeerDTOv2 saveNewBeer(BeerDTOv2 beerDTOv2);
}
