package uz.master.warehouse.services.organization;

import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.organization.Market;
import uz.master.warehouse.repository.organization.OrganizationRepository;
import uz.master.warehouse.validator.organization.MarketValidator;
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
    public DataDto<Long> create(MarketCreateDto createDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public DataDto<Long> update(MarketUpdateDto updateDto) {
        return null;
    }

    @Override
    public DataDto<List<MarketDto>> getAll() {
        return null;
    }

    @Override
    public DataDto<MarketDto> get(Long id) {
        return null;
    }
}
