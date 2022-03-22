package uz.master.warehouse.services.market;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.master.warehouse.entity.AuthUser;
import uz.master.warehouse.repository.OrganizationRepository;
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

    private final OrganizationRepository organizationRepository;
    public MarketService(MarketRepository repository, MarketMapper mapper, MarketValidator validator, OrganizationRepository organizationRepository) {
        super(repository, mapper, validator);
        this.organizationRepository = organizationRepository;
    }

    @Override
    public Long create(MarketCreateDto createDto) {
        AuthUser session = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Market market = mapper.fromCreateDto(createDto);
        market.setOrganization(organizationRepository.findById(session.getOrganization().getId()).get());
        market.setOwnerId(session.getId());
        repository.save(market);
        return null;
    }

    @Override
    public Void delete(Long id) {
        repository.deleteMarket(id);
        return null;
    }

    @Override
    public Void update(MarketUpdateDto updateDto) {
        AuthUser session = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Market market = mapper.fromUpdateDto(updateDto);
        market.setOrganization(organizationRepository.findById(session.getOrganization().getId()).get());
        market.setOwnerId(session.getId());
        repository.save(market);
        return null;
    }

    @Override
    public List<MarketDto> getAll() {
        return mapper.toDto(repository.findAllByDeletedFalse());
    }

    @Override
    public MarketDto get(Long id) {
        return mapper.toDto(repository.findByIdAndDeletedFalse(id));
    }
}
