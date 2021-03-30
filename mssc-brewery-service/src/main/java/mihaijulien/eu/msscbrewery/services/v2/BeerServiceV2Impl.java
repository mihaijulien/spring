package mihaijulien.eu.msscbrewery.services.v2;

import mihaijulien.eu.msscbrewery.web.model.v2.BeerDTOv2;
import mihaijulien.eu.msscbrewery.web.model.v2.BeerStyleEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class BeerServiceV2Impl implements BeerServiceV2 {
    Logger logger = LoggerFactory.getLogger(BeerServiceV2Impl.class);

    @Override
    public BeerDTOv2 getBeerById(UUID beerId) {
        return BeerDTOv2.builder().id(UUID.randomUUID())
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyleEnum.IPA)
                .price(BigDecimal.valueOf(45.0))
                .version(2)
                .build();
    }

    @Override
    public void updateBeer(UUID beerID, BeerDTOv2 beerDTOv2) {
        logger.info("Beer updated");
    }

    @Override
    public void deleteById(UUID beerId) {
        logger.info("Deleting a beer");
    }

    @Override
    public BeerDTOv2 saveNewBeer(BeerDTOv2 beerDTOv2) {
        return BeerDTOv2.builder().id(UUID.randomUUID())
                .beerName(beerDTOv2.getBeerName())
                .beerStyle(BeerStyleEnum.valueOf(beerDTOv2.getBeerName()))
                .price(beerDTOv2.getPrice())
                .version(2)
                .build();
    }
}
