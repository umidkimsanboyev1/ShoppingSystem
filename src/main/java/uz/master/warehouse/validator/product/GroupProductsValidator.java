package uz.master.warehouse.validator.product;

import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.groupProducts.GroupProductsCreateDto;
import uz.master.warehouse.dto.groupProducts.GroupProductsUpdateDto;
import uz.master.warehouse.validator.GenericValidator;

import java.util.Objects;

@Component
public class GroupProductsValidator implements GenericValidator<GroupProductsCreateDto, GroupProductsUpdateDto> {
    @Override
    public boolean validForCreate(GroupProductsCreateDto createDto) {
        return (Objects.nonNull(createDto.getCompanyId())
                && Objects.nonNull(createDto.getDate()));

    }

    @Override
    public boolean validForUpdate(GroupProductsUpdateDto updateDto) {
        return (Objects.nonNull(updateDto.getCompanyId()) &&
                Objects.nonNull(updateDto.getDate()));
    }
}
