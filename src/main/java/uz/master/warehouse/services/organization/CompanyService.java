package uz.master.warehouse.services.organization;

import org.springframework.stereotype.Service;
import uz.master.warehouse.entity.organization.Company;
import uz.master.warehouse.repository.organization.CompanyRepository;
import uz.master.warehouse.validator.organization.CompanyValidator;
import uz.master.warehouse.dto.company.CompanyCreateDto;
import uz.master.warehouse.dto.company.CompanyDto;
import uz.master.warehouse.dto.company.CompanyUpdateDto;
import uz.master.warehouse.mapper.organization.CompanyMapper;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;

import java.util.List;

@Service
public class CompanyService extends AbstractService<CompanyRepository, CompanyMapper, CompanyValidator> implements GenericCrudService<
        Company,
        CompanyDto,
        CompanyCreateDto,
        CompanyUpdateDto,
        Long> {

    public CompanyService(CompanyRepository repository, CompanyMapper mapper, CompanyValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public Long create(CompanyCreateDto createDto) {
        Company company = mapper.fromCreateDto(createDto);
        repository.save(company);
        return null;
    }

    @Override
    public Void delete(Long id) {
        repository.deleteCompany(id);
        return null;
    }

    @Override
    public Void update(CompanyUpdateDto updateDto) {
        Company company = mapper.fromUpdateDto(updateDto);
        repository.save(company);
        return null;
    }

    @Override
    public List<CompanyDto> getAll() {
        return mapper.toDto(repository.findAllByDeletedFalse());
    }

    @Override
    public CompanyDto get(Long id) {
        return mapper.toDto(repository.findByIdAndDeletedFalse(id));
    }
}