package uz.master.warehouse.services.market;

import org.springframework.stereotype.Service;
import uz.master.warehouse.validator.MarketValidator;
import uz.master.warehouse.dto.market.MarketCreateDto;
import uz.master.warehouse.dto.market.MarketDto;
import uz.master.warehouse.dto.market.MarketUpdateDto;
import uz.master.warehouse.entity.Market;
import uz.master.warehouse.mapper.MarketMapper;
import uz.master.warehouse.repository.MarketRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;

import java.util.List;

@Service
public class MarketService extends AbstractService<
        MarketRepository,
        MarketMapper,
        MarketValidator
        > implements GenericCrudService<
        Market,
        MarketDto,
        MarketCreateDto,
        MarketUpdateDto,
        Long
        > {
    public MarketService(MarketRepository repository, MarketMapper mapper, MarketValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public Long create(MarketCreateDto createDto) {
        return null;
    }

    @Override
    public Void delete(Long id) {
        return null;
    }

    @Override
    public Void update(MarketUpdateDto updateDto) {
        return null;
    }

    @Override
    public List<MarketDto> getAll() {
        return null;
    }

    @Override
    public MarketDto get(Long id) {
        return null;
    }
}
