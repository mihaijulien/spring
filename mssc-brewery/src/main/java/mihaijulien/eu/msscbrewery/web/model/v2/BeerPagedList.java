package mihaijulien.eu.msscbrewery.web.model.v2;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BeerPagedList extends PageImpl<BeerDTOv2> {

    public BeerPagedList(List<BeerDTOv2> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BeerPagedList(List<BeerDTOv2> content) {
        super(content);
    }
}
