package mihaijulien.eu.msscbrewery.services;

import mihaijulien.eu.msscbrewery.web.model.BeerDTO;

import java.util.UUID;

public interface BeerService {
    BeerDTO getBeerById(UUID beerId);

    BeerDTO saveNewBeer(BeerDTO beerDTO);

    void updateBeer(UUID beerID, BeerDTO beerDTO);
}
