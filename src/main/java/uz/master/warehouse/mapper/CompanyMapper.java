package uz.master.warehouse.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.company.CompanyCreateDto;
import uz.master.warehouse.dto.company.CompanyDto;
import uz.master.warehouse.dto.company.CompanyUpdateDto;
import uz.master.warehouse.entity.Company;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CompanyMapper extends BaseMapper {


    Company fromCreateDto(CompanyCreateDto createDto);

    Company fromUpdateDto(CompanyUpdateDto updateDto);

    List<CompanyDto> toDto(List<Company> all);

    CompanyDto toDto (Company company);
}
