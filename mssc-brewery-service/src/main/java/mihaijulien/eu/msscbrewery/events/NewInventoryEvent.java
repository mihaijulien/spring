package mihaijulien.eu.msscbrewery.events;

import mihaijulien.eu.msscbrewery.web.model.v2.BeerDTOv2;

public class NewInventoryEvent extends BeerEvent{

    public NewInventoryEvent(BeerDTOv2 beerDTO) {
        super(beerDTO);
    }
}
