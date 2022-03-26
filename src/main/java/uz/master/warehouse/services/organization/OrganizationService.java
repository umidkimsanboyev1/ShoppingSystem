package uz.master.warehouse.services.organization;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.organization.OrganizationCreateDto;
import uz.master.warehouse.dto.organization.OrganizationDto;
import uz.master.warehouse.dto.organization.OrganizationUpdateDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.organization.Organization;
import uz.master.warehouse.mapper.organization.OrganizationMapper;
import uz.master.warehouse.repository.organization.OrganizationRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;
import uz.master.warehouse.validator.organization.OrganizationValidator;

import java.util.List;

@Service
public class OrganizationService extends AbstractService<OrganizationRepository, OrganizationMapper, OrganizationValidator>
        implements GenericCrudService<
        Organization,
        OrganizationDto,
        OrganizationCreateDto,
        OrganizationUpdateDto,
        Long> {

    public OrganizationService(OrganizationRepository repository, OrganizationMapper mapper, OrganizationValidator validator) {
        super(repository, mapper, validator);
    }


    @Override
    public DataDto<Long> create(OrganizationCreateDto createDto) {
        Organization organization = mapper.fromCreateDto(createDto);
        organization.setName(createDto.getName());
        organization.setOwnerId(createDto.getOwnerId());
        Organization save = repository.save(organization);
        return new DataDto<>(save.getId());
    }

    @Override
    public DataDto<Void> delete(Long id) {
        repository.deleteById(id);
        return new DataDto<>();
    }

    @Override
    public DataDto<Long> update(OrganizationUpdateDto updateDto) {
        Organization organization = mapper.fromUpdateDto(updateDto);
        organization.setName(updateDto.getName());
        Organization save = repository.save(organization);
        return new DataDto<>(save.getId());
    }

    @Override
    public DataDto<List<OrganizationDto>> getAll() {
        List<Organization> list = repository.findAll();
        return new DataDto<>(mapper.toDto(list));
    }

    @Override
    public DataDto<OrganizationDto> get(Long id) {
        Organization organization = repository.findById(id).orElseThrow(() -> {
            throw new UsernameNotFoundException("Not found");
        });
        return new DataDto<>(mapper.toDto(organization));
    }
}
