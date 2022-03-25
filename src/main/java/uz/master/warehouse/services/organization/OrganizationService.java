package uz.master.warehouse.services.organization;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.master.warehouse.entity.auth.AuthUser;
import uz.master.warehouse.entity.organization.Organization;
import uz.master.warehouse.repository.auth.AuthUserRepository;
import uz.master.warehouse.validator.organization.OrganizationValidator;
import uz.master.warehouse.dto.organization.OrganizationCreateDto;
import uz.master.warehouse.dto.organization.OrganizationDto;
import uz.master.warehouse.dto.organization.OrganizationUpdateDto;
import uz.master.warehouse.mapper.organization.OrganizationMapper;
import uz.master.warehouse.repository.organization.OrganizationRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService extends AbstractService<OrganizationRepository, OrganizationMapper, OrganizationValidator>
        implements GenericCrudService<
        Organization,
        OrganizationDto,
        OrganizationCreateDto,
        OrganizationUpdateDto,
        Long> {

    private final AuthUserRepository userRepository;

    public OrganizationService(OrganizationRepository repository, OrganizationMapper mapper, OrganizationValidator validator, AuthUserRepository userRepository) {
        super(repository, mapper, validator);
        this.userRepository = userRepository;
    }



    @Override
    public Long create(OrganizationCreateDto createDto) {
        Optional<AuthUser> byId = userRepository.findById(createDto.getOwnerId());
        if (byId.isEmpty()){
            throw new UsernameNotFoundException("USER_NOT_FOUND");
        }
        Organization organization = new Organization();
        organization.setName(createDto.getName());
        organization.setOwnerId(byId.get().getId());
        repository.save(organization);
        return null;
    }

    @Override
    public Void delete(Long id) {
        repository.deleteOrganization(id);
        return null;
    }

    @Override
    public Void update(OrganizationUpdateDto updateDto) {
        Optional<Organization> byId = repository.findById(updateDto.getId());
        if(byId.isEmpty()){
            throw new RuntimeException("ORGANIZATION_NOT_FOUND");
        }
        Organization organization = byId.get();
        organization.setName(updateDto.getName());
        repository.save(organization);
        return null;
    }

    @Override
    public List<OrganizationDto> getAll() {
        List<Organization> all = repository.findAllByDeletedFalse();
        return mapper.toDto(all);
    }

    @Override
    public OrganizationDto get(Long id) {
        Optional<Organization> byId = repository.findById(id);
        if(byId.isEmpty()){
            throw new RuntimeException("ORGANIZATION_NOT_FOUND");
        }
        Organization organization = byId.get();
        return mapper.toDto(organization);
    }
}
