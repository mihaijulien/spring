package mihaijulien.eu.msscbrewery.client;

import mihaijulien.eu.msscbrewery.client.BreweryClient;
import mihaijulien.eu.msscbrewery.model.BeerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient client;

    @Test
    void getBeerByIdTest() {
        BeerDTO beerDTO = client.getBeerById(UUID.randomUUID());

        assertNotNull(beerDTO);
    }

    @Test
    void saveNewBeerTest(){
        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("New beer")
                .build();
        URI uri = client.saveNewBeer(beerDTO);

        assertNotNull(uri);
        
        System.out.print(uri.toString());
    }

    @Test
    void updateBeerTest(){
        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("New beer")
                .build();

        client.updateBeer(UUID.randomUUID(), beerDTO);
    }

    @Test
    void deleteBeerTest(){
        client.deleteBeer(UUID.randomUUID());
    }
}