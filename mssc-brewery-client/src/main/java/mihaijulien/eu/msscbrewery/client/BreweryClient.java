package mihaijulien.eu.msscbrewery.client;

import mihaijulien.eu.msscbrewery.model.BeerDTO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    public final String BEER_PATH_V2 = "/api/v2/beer/";
    private String apiHost;
    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDTO getBeerById(UUID id){
        return restTemplate.getForObject(apiHost + BEER_PATH_V2 + id.toString(), BeerDTO.class);
    }

    public URI saveNewBeer(BeerDTO beerDTO){
        return restTemplate.postForLocation(apiHost + BEER_PATH_V2, beerDTO);
    }

    public void updateBeer(UUID uuid, BeerDTO beerDTO){
        restTemplate.put(apiHost + BEER_PATH_V2 + "/" + uuid.toString(), beerDTO);
    }

    public void deleteBeer(UUID uuid){
        restTemplate.delete(apiHost + BEER_PATH_V2 + "/" + uuid.toString());
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }
}
