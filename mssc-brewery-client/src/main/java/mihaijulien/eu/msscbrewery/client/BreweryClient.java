package mihaijulien.eu.msscbrewery.client;

import mihaijulien.eu.msscbrewery.model.BeerDTO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }
}
