package uz.master.warehouse.mapper.organization;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.organization.OrganizationCreateDto;
import uz.master.warehouse.dto.organization.OrganizationDto;
import uz.master.warehouse.dto.organization.OrganizationUpdateDto;
import uz.master.warehouse.entity.organization.Organization;
import uz.master.warehouse.mapper.BaseMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface OrganizationMapper extends BaseMapper<Organization, OrganizationDto, OrganizationCreateDto, OrganizationUpdateDto> {
    @Override
    Organization fromCreateDto(OrganizationCreateDto createDto);

    @Override
    List<OrganizationDto> toDto(List<Organization> all);

    @Override
    OrganizationDto toDto(Organization organization);

    @Override
    Organization fromUpdateDto(OrganizationUpdateDto organizationUpdateDto);
}
