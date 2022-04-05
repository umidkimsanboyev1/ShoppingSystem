package uz.master.warehouse.services.organization;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.market.MarketCreateDto;
import uz.master.warehouse.dto.market.MarketDto;
import uz.master.warehouse.dto.market.MarketUpdateDto;
import uz.master.warehouse.dto.responce.AppErrorDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.organization.Market;
import uz.master.warehouse.entity.organization.Organization;
import uz.master.warehouse.mapper.organization.MarketMapper;
import uz.master.warehouse.repository.organization.MarketRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;
import uz.master.warehouse.validator.organization.MarketValidator;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Service
public class MarketService extends AbstractService<
        MarketRepository,
        MarketMapper
        > implements GenericCrudService<
        Market,
        MarketDto,
        MarketCreateDto,
        MarketUpdateDto,
        Long
        > {


    public MarketService(MarketRepository repository, MarketMapper mapper) {
        super(repository, mapper);
    }


    @Override
    public DataDto<Long> create(@Valid MarketCreateDto createDto) {
        Market market = mapper.fromCreateDto(createDto);
        Organization organization = repository.findByOrgId(createDto.getOrganizationId());
        if (Objects.isNull(organization)) {
            return new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, "Organization not found", "organization/get"));
        }
        market.setOrganizationId(organization.getId());
        market.setOwnerId(organization.getOwnerId());
        market.setName(createDto.getName());
        market.setDescription(createDto.getDescription());
        market.setLocation(createDto.getLocation());
        Market save = repository.save(market);
        return new DataDto<>(save.getId());
    }

    @Override
    public DataDto<Void> delete(Long id) {
        repository.deleteMarket(id);
        return new DataDto<>();
    }

    @Override
    public DataDto<Long> update(@Valid MarketUpdateDto updateDto) {

        Market market = mapper.fromUpdateDto(updateDto);
        market.setName(updateDto.getName());
        market.setDescription(updateDto.getDescription());
        market.setLocation(updateDto.getLocation());
        repository.update(market.getId(), market.getName(), market.getLocation(), market.getDescription());
        return new DataDto<>(market.getId());
    }

    @Override
    public DataDto<List<MarketDto>> getAll() {
        List<Market> all = repository.findAllByDeletedFalse();
        return new DataDto<>(mapper.toDto(all));
    }

    @Override
    public DataDto<MarketDto> get(Long id) {
        Market market = repository.findByIdAndDeletedFalse(id);
        if (Objects.isNull(market)) {
            return new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, "Market not found", "market/get"));

        }
        return new DataDto<>(mapper.toDto(market));
    }
}
