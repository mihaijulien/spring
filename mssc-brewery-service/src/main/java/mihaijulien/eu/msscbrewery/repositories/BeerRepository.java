package mihaijulien.eu.msscbrewery.repositories;

import mihaijulien.eu.msscbrewery.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}
