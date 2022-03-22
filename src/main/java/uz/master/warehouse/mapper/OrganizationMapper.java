package uz.master.warehouse.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.organization.OrganizationCreateDto;
import uz.master.warehouse.dto.organization.OrganizationDto;
import uz.master.warehouse.entity.Organization;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface OrganizationMapper extends BaseMapper {
    Organization fromCreateDto(OrganizationCreateDto createDto);

    List<OrganizationDto> toDto(List<Organization> all);

    OrganizationDto toDto(Organization organization);
}
