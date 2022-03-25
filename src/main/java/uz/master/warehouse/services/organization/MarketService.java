package uz.master.warehouse.services.organization;

import org.springframework.stereotype.Service;
import uz.master.warehouse.entity.organization.Market;
import uz.master.warehouse.repository.organization.OrganizationRepository;
import uz.master.warehouse.validator.MarketValidator;
import uz.master.warehouse.dto.market.MarketCreateDto;
import uz.master.warehouse.dto.market.MarketDto;
import uz.master.warehouse.dto.market.MarketUpdateDto;
import uz.master.warehouse.mapper.organization.MarketMapper;
import uz.master.warehouse.repository.organization.MarketRepository;
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

    private final OrganizationRepository organizationRepository;
    public MarketService(MarketRepository repository, MarketMapper mapper, MarketValidator validator, OrganizationRepository organizationRepository) {
        super(repository, mapper, validator);
        this.organizationRepository = organizationRepository;
    }


    @Override
    public List<MarketDto> getAll() {
        return mapper.toDto(repository.findAllByDeletedFalse());
    }

    @Override
    public MarketDto get(Long id) {
        return mapper.toDto(repository.findByIdAndDeletedFalse(id));
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
}
