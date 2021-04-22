package mihaijulien.eu.msscbrewery.events;

import lombok.*;
import mihaijulien.eu.msscbrewery.web.model.v2.BeerDTOv2;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    static final long serialVersionUUID = -136067348552556409L;

    private final BeerDTOv2 beerDTO;
}
