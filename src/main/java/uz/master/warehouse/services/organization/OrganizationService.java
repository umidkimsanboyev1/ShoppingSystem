package uz.master.warehouse.services.organization;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.responce.DataDto;
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
    public DataDto<Long> create(OrganizationCreateDto createDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public DataDto<Long> update(OrganizationUpdateDto updateDto) {
        return null;
    }

    @Override
    public DataDto<List<OrganizationDto>> getAll() {
        return null;
    }

    @Override
    public DataDto<OrganizationDto> get(Long id) {
        return null;
    }
}
