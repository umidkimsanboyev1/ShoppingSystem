package uz.master.warehouse.mapper.organization;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.comment.CommentUpdateDto;
import uz.master.warehouse.dto.company.CompanyCreateDto;
import uz.master.warehouse.dto.company.CompanyDto;
import uz.master.warehouse.dto.company.CompanyUpdateDto;
import uz.master.warehouse.entity.clientBar.Comment;
import uz.master.warehouse.entity.organization.Company;
import uz.master.warehouse.mapper.BaseMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CompanyMapper extends BaseMapper<Company,CompanyDto,CompanyCreateDto,CompanyUpdateDto> {
    @Override
    CompanyDto toDto(Company company);

    @Override
    List<CompanyDto> toDto(List<Company> e);

    @Override
    Company fromCreateDto(CompanyCreateDto companyCreateDto);

    @Override
    Company fromUpdateDto(CompanyUpdateDto companyUpdateDto);

    Company fromUpdateDto(CompanyUpdateDto companyUpdateDto , @MappingTarget Company company);
}
