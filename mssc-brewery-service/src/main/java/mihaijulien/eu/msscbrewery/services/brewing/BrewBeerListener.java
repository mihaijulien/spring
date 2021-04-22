package mihaijulien.eu.msscbrewery.services.brewing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mihaijulien.eu.msscbrewery.config.JmsConfig;
import mihaijulien.eu.msscbrewery.domain.Beer;
import mihaijulien.eu.msscbrewery.events.BrewBeerEvent;
import mihaijulien.eu.msscbrewery.events.NewInventoryEvent;
import mihaijulien.eu.msscbrewery.repositories.BeerRepository;
import mihaijulien.eu.msscbrewery.web.model.v2.BeerDTOv2;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent event){
        BeerDTOv2 beerDTOv2 = event.getBeerDTO();

        Beer beer = beerRepository.getOne(beerDTOv2.getId());

        beerDTOv2.setQuantityOnHand(beer.getQuantityToBrew());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDTOv2);

        log.debug("Brewed beer " + beer.getMinOnHand() + ": QOH: " + beerDTOv2.getQuantityOnHand());

        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
    }
}
