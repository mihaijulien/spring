package mihaijulien.eu.msscbrewery.services.v2;

import mihaijulien.eu.msscbrewery.domain.Beer;
import mihaijulien.eu.msscbrewery.repositories.BeerRepository;
import mihaijulien.eu.msscbrewery.web.controller.v2.NotFoundException;
import mihaijulien.eu.msscbrewery.web.mappers.BeerMapper;
import mihaijulien.eu.msscbrewery.web.model.v2.BeerDTOv2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BeerServiceV2Impl implements BeerServiceV2 {
    Logger logger = LoggerFactory.getLogger(BeerServiceV2Impl.class);
    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    public BeerServiceV2Impl(BeerMapper beerMapper, BeerRepository beerRepository) {
        this.beerMapper = beerMapper;
        this.beerRepository = beerRepository;
    }

    @Override
    public BeerDTOv2 getBeerById(UUID beerId) {
        return beerMapper.beerToBeerDTO(beerRepository.findById(beerId).orElseThrow(() -> new NotFoundException()));
    }

    @Override
    public BeerDTOv2 updateBeer(UUID beerID, BeerDTOv2 beerDTOv2) {
        Beer beer = beerRepository.findById(beerID).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDTOv2.getBeerName());
        beer.setBeerStyle(beerDTOv2.getBeerStyle().name());
        beer.setPrice(beerDTOv2.getPrice());
        beer.setUpc(beerDTOv2.getUpc());

        return beerMapper.beerToBeerDTO(beerRepository.save(beer));
    }

    @Override
    public void deleteById(UUID beerId) {
        beerRepository.deleteById(beerId);
    }

    @Override
    public BeerDTOv2 saveNewBeer(BeerDTOv2 beerDTOv2) {
        return beerMapper.beerToBeerDTO(beerRepository.save(beerMapper.beerDTOtoBeer(beerDTOv2)));
    }
}
