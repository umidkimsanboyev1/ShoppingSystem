package uz.master.warehouse.services.organization;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.master.warehouse.criteria.GenericCriteria;
import uz.master.warehouse.dto.market.MarketCreateDto;
import uz.master.warehouse.dto.market.MarketDto;
import uz.master.warehouse.dto.market.MarketUpdateDto;
import uz.master.warehouse.dto.market.MarketUploadDTO;
import uz.master.warehouse.dto.responce.AppErrorDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.organization.Market;
import uz.master.warehouse.entity.organization.Organization;
import uz.master.warehouse.mapper.organization.MarketMapper;
import uz.master.warehouse.repository.organization.MarketRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;
import uz.master.warehouse.services.file.FileStorageService;
import uz.master.warehouse.session.SessionUser;

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
        GenericCriteria,
        Long
        > {

    private final SessionUser sessionUser;
    private final FileStorageService fileStorageService;


    public MarketService(MarketRepository repository, MarketMapper mapper, SessionUser sessionUser, FileStorageService fileStorageService) {
        super(repository, mapper);
        this.sessionUser = sessionUser;
        this.fileStorageService = fileStorageService;
    }


    @Override
    public DataDto<Long> create(@Valid MarketCreateDto createDto) {
        Long orgId = sessionUser.getOrgId();
        Market market = mapper.fromCreateDto(createDto);
        Organization organization = repository.findByOrgId(orgId);
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
        DataDto<MarketDto> marketDtoDataDto = get(id);
        Long organizationId = marketDtoDataDto.getData().getOrganizationId();
        Long orgId = sessionUser.getOrgId();
        if (!organizationId.equals(orgId)) {
            return new DataDto<>(new AppErrorDto(HttpStatus.FORBIDDEN, "You have no such privilege", "market/delete"));
        }
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

    @Override
    public DataDto<List<MarketDto>> getWithCriteria(GenericCriteria criteria) {
        return null;
    }
}
