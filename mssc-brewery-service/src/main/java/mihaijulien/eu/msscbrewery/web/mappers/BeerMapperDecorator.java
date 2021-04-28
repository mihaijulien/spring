package mihaijulien.eu.msscbrewery.web.mappers;

import mihaijulien.eu.msscbrewery.domain.Beer;
import mihaijulien.eu.msscbrewery.services.inventory.BeerInventoryService;
import mihaijulien.eu.msscbrewery.web.model.v2.BeerDTOv2;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BeerMapperDecorator implements BeerMapper{
    private BeerInventoryService beerInventoryService;
    private BeerMapper mapper;

    // setter implementation for di to work properly with MapStruct
    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService){
        this.beerInventoryService = beerInventoryService;
    }

    @Autowired
    public void setMapper(BeerMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public BeerDTOv2 beerToBeerDTO(Beer beer){
        return mapper.beerToBeerDTO(beer);
    }

    @Override
    public BeerDTOv2 beerToBeerDtoWithInventory(Beer beer){
        BeerDTOv2 dto = mapper.beerToBeerDTO(beer);
        dto.setQuantityOnHand(beerInventoryService.getOnHandInventory(beer.getId()));
        return dto;
    }

    @Override
    public Beer beerDTOtoBeer(BeerDTOv2 beerDTOv2){
        return mapper.beerDTOtoBeer(beerDTOv2);
    }
}
