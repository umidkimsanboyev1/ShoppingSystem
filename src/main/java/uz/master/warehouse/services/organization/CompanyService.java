package uz.master.warehouse.services.organization;

import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.responce.DataDto;
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
    public DataDto<Long> create(CompanyCreateDto createDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public DataDto<Long> update(CompanyUpdateDto updateDto) {
        return null;
    }

    @Override
    public DataDto<List<CompanyDto>> getAll() {
        return null;
    }

    @Override
    public DataDto<CompanyDto> get(Long id) {
        return null;
    }
}
