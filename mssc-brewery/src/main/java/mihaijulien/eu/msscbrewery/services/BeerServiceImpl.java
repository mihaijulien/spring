package mihaijulien.eu.msscbrewery.services;

import lombok.extern.slf4j.Slf4j;
import mihaijulien.eu.msscbrewery.web.model.BeerDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService{
    @Override
    public BeerDTO getBeerById(UUID beerId) {
        return BeerDTO.builder().id(UUID.randomUUID())
                .beerName("Galaxy Cat")
                .beerStyle("Pale Ale")
                .build();
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beerDTO) {
        return BeerDTO.builder().id(UUID.randomUUID())
                .beerName(beerDTO.getBeerName())
                .beerStyle(beerDTO.getBeerName())
                .build();
    }

    @Override
    public void updateBeer(UUID beerID, BeerDTO beerDTO) {
        // TODO - implement method
    }

    @Override
    public void deleteById(UUID beerId) {
        log.debug("Deleting a beer");
    }
}
