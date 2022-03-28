package uz.master.warehouse.services.organization;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.organization.OrganizationCreateDto;
import uz.master.warehouse.dto.organization.OrganizationDto;
import uz.master.warehouse.dto.organization.OrganizationUpdateDto;
import uz.master.warehouse.dto.responce.AppErrorDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.organization.Organization;
import uz.master.warehouse.mapper.organization.OrganizationMapper;
import uz.master.warehouse.repository.organization.OrganizationRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;
import uz.master.warehouse.validator.organization.OrganizationValidator;

import java.util.List;
import java.util.Objects;

@Service
public class OrganizationService extends AbstractService<
        OrganizationRepository,
        OrganizationMapper,
        OrganizationValidator>
        implements GenericCrudService<
        Organization,
        OrganizationDto,
        OrganizationCreateDto,
        OrganizationUpdateDto,
        Long> {

    public OrganizationService(OrganizationRepository repository,  OrganizationMapper mapper, OrganizationValidator validator) {
        super(repository, mapper, validator);
    }


    @Override
    public DataDto<Long> create(OrganizationCreateDto createDto) {
        if (!validator.validForCreate(createDto)) {
            return new DataDto<>(new AppErrorDto("Not Valid On Create", HttpStatus.CONFLICT));
        }
        Organization organization = mapper.fromCreateDto(createDto);
        organization.setName(createDto.getName());
        organization.setOwnerId(createDto.getOwnerId());
        Organization save = repository.save(organization);
        return new DataDto<>(save.getId());
    }

    @Override
    public DataDto<Void> delete(Long id) {
        repository.deleteOrganization(id);
        return new DataDto<>();
    }

    @Override
    public DataDto<Long> update(OrganizationUpdateDto updateDto) {
        if (!validator.validForUpdate(updateDto)) {
            return new DataDto<>(new AppErrorDto("Not Valid On Update", HttpStatus.CONFLICT));
        }
        Organization organization = mapper.fromUpdateDto(updateDto);
        organization.setName(updateDto.getName());
        repository.updateOrg(organization.getId(),organization.getName());
        return new DataDto<>(organization.getId());
    }

    @Override
    public DataDto<List<OrganizationDto>> getAll() {
        List<Organization> list = repository.findAllByDeletedFalse();
        return new DataDto<>(mapper.toDto(list));
    }

    @Override
    public DataDto<OrganizationDto> get(Long id) {
        Organization organization = repository.findByIdAndDeletedFalse(id);
        if (Objects.isNull(organization)){
            return new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, "Organization not found", "organization/get"));

        }
        return new DataDto<>(mapper.toDto(organization));
    }
}
