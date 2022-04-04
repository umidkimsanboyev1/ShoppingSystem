package uz.master.warehouse.services.organization;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.master.warehouse.dto.organization.OrganizationCreateDto;
import uz.master.warehouse.dto.organization.OrganizationDto;
import uz.master.warehouse.dto.organization.OrganizationUpdateDto;
import uz.master.warehouse.dto.responce.AppErrorDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.auth.AuthUser;
import uz.master.warehouse.entity.organization.Organization;
import uz.master.warehouse.mapper.organization.OrganizationMapper;
import uz.master.warehouse.repository.auth.AuthUserRepository;
import uz.master.warehouse.repository.organization.OrganizationRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;
import uz.master.warehouse.services.file.FileStorageService;
import uz.master.warehouse.validator.organization.OrganizationValidator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrganizationService extends AbstractService<
        OrganizationRepository,
        OrganizationMapper>
        implements GenericCrudService<
        Organization,
        OrganizationDto,
        OrganizationCreateDto,
        OrganizationUpdateDto,
        Long> {

    private final FileStorageService fileService;
    private final AuthUserRepository userRepository;

    public OrganizationService(OrganizationRepository repository, OrganizationMapper mapper, FileStorageService fileService, AuthUserRepository userRepository) {
        super(repository, mapper);
        this.fileService = fileService;
        this.userRepository = userRepository;
    }


    @Override
    public DataDto<Long> create(OrganizationCreateDto createDto) {
        Optional<AuthUser> ownerById = userRepository.findById(createDto.getOwnerId());
        if (ownerById.isEmpty()){
            return new DataDto<>(new AppErrorDto("USER_NOT_FOUND", HttpStatus.BAD_REQUEST));
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

        Organization organization = mapper.fromUpdateDto(updateDto);
        organization.setName(updateDto.getName());
        repository.updateOrg(organization.getId(), organization.getName());
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
        if (Objects.isNull(organization)) {
            return new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, "Organization not found", "organization/get"));

        }
        return new DataDto<>(mapper.toDto(organization));
    }

    public boolean loadLogo(Long id, MultipartFile file) {
        try {
            String generatedName = fileService.store(file);
            repository.updateLogo(generatedName, id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public AuthUserRepository getUserRepository() {
        return userRepository;
    }
}
