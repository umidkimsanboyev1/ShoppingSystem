package uz.master.warehouse.validator.organization;

import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.company.CompanyCreateDto;
import uz.master.warehouse.dto.company.CompanyUpdateDto;
import uz.master.warehouse.dto.groupProducts.GroupProductsCreateDto;
import uz.master.warehouse.dto.groupProducts.GroupProductsUpdateDto;
import uz.master.warehouse.validator.BaseValidator;
import uz.master.warehouse.validator.GenericValidator;

import java.util.Objects;

@Component
public class CompanyValidator implements GenericValidator<CompanyCreateDto, CompanyUpdateDto> {
    @Override
    public boolean validForCreate(CompanyCreateDto createDto) {
        return (Objects.nonNull(createDto.getName())
                && Objects.nonNull(createDto.getRegisterNumber()));

    }

    @Override
    public boolean validForUpdate(CompanyUpdateDto updateDto) {
        return (Objects.nonNull(updateDto.getName()));
    }
}
