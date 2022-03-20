package uz.master.warehouse.services.organization;

import org.springframework.stereotype.Service;
import uz.master.warehouse.validator.OrganizationValidator;
import uz.master.warehouse.dto.organization.OrganizationCreateDto;
import uz.master.warehouse.dto.organization.OrganizationDto;
import uz.master.warehouse.dto.organization.OrganizationUpdateDto;
import uz.master.warehouse.entity.Organization;
import uz.master.warehouse.mapper.OrganizationMapper;
import uz.master.warehouse.repository.OrganizationRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;

import java.util.List;

@Service
public class OrganizationService extends AbstractService<OrganizationRepository, OrganizationMapper, OrganizationValidator> implements GenericCrudService<Organization, OrganizationDto, OrganizationCreateDto, OrganizationUpdateDto, Long> {
    public OrganizationService(OrganizationRepository repository, OrganizationMapper mapper, OrganizationValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public Long create(OrganizationCreateDto createDto) {
        return null;
    }

    @Override
    public Void delete(Long id) {
        return null;
    }

    @Override
    public Void update(OrganizationUpdateDto updateDto) {
        return null;
    }

    @Override
    public List<OrganizationDto> getAll() {
        return null;
    }

    @Override
    public OrganizationDto get(Long id) {
        return null;
    }
}
