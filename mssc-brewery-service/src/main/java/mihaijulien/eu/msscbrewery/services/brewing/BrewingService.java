package mihaijulien.eu.msscbrewery.services.brewing;

import mihaijulien.eu.msscbrewery.config.JmsConfig;
import mihaijulien.eu.msscbrewery.events.BrewBeerEvent;
import mihaijulien.eu.msscbrewery.repositories.BeerRepository;
import mihaijulien.eu.msscbrewery.domain.Beer;
import mihaijulien.eu.msscbrewery.web.mappers.BeerMapper;
import org.springframework.jms.core.JmsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mihaijulien.eu.msscbrewery.services.inventory.BeerInventoryService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000) //every 5 seconds
    public void checkForLowInventory(){
        List<Beer> beers = beerRepository.findAll();

        beers.forEach(beer -> {
            Integer invQoH = beerInventoryService.getOnHandInventory(beer.getId());

            log.debug("Minimum on hand is: " + beer.getMinOnHand());
            log.debug("Inventory is: " + invQoH);

            if(beer.getMinOnHand() >= invQoH) {
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDTO(beer)));
            }
        });
    }
}
