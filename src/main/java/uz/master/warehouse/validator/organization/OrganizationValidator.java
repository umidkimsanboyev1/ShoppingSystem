package uz.master.warehouse.validator.organization;

import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.organization.OrganizationCreateDto;
import uz.master.warehouse.dto.organization.OrganizationUpdateDto;
import uz.master.warehouse.validator.BaseValidator;
import uz.master.warehouse.validator.GenericValidator;

import java.util.Objects;

@Component
public class OrganizationValidator implements GenericValidator<OrganizationCreateDto, OrganizationUpdateDto> {
    @Override
    public boolean validForCreate(OrganizationCreateDto createDto) {
        return (Objects.nonNull(createDto.getName())
                && Objects.nonNull(createDto.getOwnerId()));
    }

    @Override
    public boolean validForUpdate(OrganizationUpdateDto updateDto) {
        return Objects.nonNull(updateDto.getName());
    }
}
