package mihaijulien.eu.msscbrewery.events;

import mihaijulien.eu.msscbrewery.web.model.v2.BeerDTOv2;

public class BrewBeerEvent extends BeerEvent{

    public BrewBeerEvent(BeerDTOv2 beerDTO) {
        super(beerDTO);
    }
}
