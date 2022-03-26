package uz.master.warehouse.validator.product;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.product.ProductCreateDto;
import uz.master.warehouse.dto.product.ProductUpdateDto;
import uz.master.warehouse.validator.BaseValidator;
import uz.master.warehouse.validator.GenericValidator;

import java.util.Objects;

@Component
public class ProductValidator implements GenericValidator<ProductCreateDto, ProductUpdateDto> {
    @Override
    public boolean validForCreate(ProductCreateDto createDto) {
        return (Objects.nonNull(createDto.getFirmId())
                || Objects.nonNull(createDto.getModel())
                || Objects.nonNull(createDto.getItem_count()));

    }

    @Override
    public boolean validForUpdate(ProductUpdateDto updateDto) {
        return (Objects.nonNull(updateDto.getDefault_price()));
    }
}
