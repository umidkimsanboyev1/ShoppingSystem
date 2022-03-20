package uz.master.warehouse.services.company;

import org.springframework.stereotype.Service;
import uz.master.warehouse.validator.CompanyValidator;
import uz.master.warehouse.dto.company.CompanyCreateDto;
import uz.master.warehouse.dto.company.CompanyDto;
import uz.master.warehouse.dto.company.CompanyUpdateDto;
import uz.master.warehouse.entity.Company;
import uz.master.warehouse.mapper.CompanyMapper;
import uz.master.warehouse.repository.CompanyRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;

import java.util.List;

@Service
public class CompanyService extends AbstractService<
        CompanyRepository,
        CompanyMapper,
        CompanyValidator
        > implements GenericCrudService<
        Company,
        CompanyDto,
        CompanyCreateDto,
        CompanyUpdateDto,
        Long
        >{
    public CompanyService(CompanyRepository repository, CompanyMapper mapper, CompanyValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public Long create(CompanyCreateDto createDto) {
        return null;
    }

    @Override
    public Void delete(Long id) {
        return null;
    }

    @Override
    public Void update(CompanyUpdateDto updateDto) {
        return null;
    }

    @Override
    public List<CompanyDto> getAll() {
        return null;
    }

    @Override
    public CompanyDto get(Long id) {
        return null;
    }
}
