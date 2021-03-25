package mihaijulien.eu.msscbrewery.web.controller;

import mihaijulien.eu.msscbrewery.services.BeerService;
import mihaijulien.eu.msscbrewery.web.model.BeerDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Deprecated
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDTO> getBeer(@PathVariable("beerId") UUID beerId){

        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDTO> handlePost(@RequestBody BeerDTO beerDTO){
        BeerDTO savedBeerDTO = beerService.saveNewBeer(beerDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("location", "/api/v1/beer" + savedBeerDTO.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDTO> handlePut(@PathVariable("beerId") UUID beerID,  @RequestBody BeerDTO beerDTO){
        beerService.updateBeer(beerID, beerDTO);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable UUID beerId){
        beerService.deleteById(beerId);
    }
}
