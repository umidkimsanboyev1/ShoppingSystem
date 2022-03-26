package uz.master.warehouse.services.organization;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.responce.AppErrorDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.organization.Company;
import uz.master.warehouse.entity.product.Product;
import uz.master.warehouse.repository.organization.CompanyRepository;
import uz.master.warehouse.validator.organization.CompanyValidator;
import uz.master.warehouse.dto.company.CompanyCreateDto;
import uz.master.warehouse.dto.company.CompanyDto;
import uz.master.warehouse.dto.company.CompanyUpdateDto;
import uz.master.warehouse.mapper.organization.CompanyMapper;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;

import java.util.List;
import java.util.Objects;

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
       Company company = mapper.fromCreateDto(createDto);
       company.setName(createDto.getName());
       company.setRegistrationNumber(createDto.getRegisterNumber());
       Company save = repository.save(company);
       return new DataDto<>(save.getId());

    }

    @Override
    public DataDto<Void> delete(Long id) {
        repository.deleteCompany(id);
        return new DataDto<>();
    }

    @Override
    public DataDto<Long> update(CompanyUpdateDto updateDto) {
      Company company = mapper.fromUpdateDto(updateDto);
      company.setName(updateDto.getName());
      Company save = repository.save(company);
        return new DataDto<>(save.getId());
    }

    @Override
    public DataDto<List<CompanyDto>> getAll() {
        List<Company> list = repository.findAllByDeletedFalse();
        return new DataDto<>(mapper.toDto(list));
    }

    @Override
    public DataDto<CompanyDto> get(Long id) {
        Company company = repository.findByIdAndDeletedFalse(id);
        if (Objects.isNull(company)){
            return new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, "Company not found", "company/get"));
        }
        return new DataDto<>(mapper.toDto(company));
    }
}
