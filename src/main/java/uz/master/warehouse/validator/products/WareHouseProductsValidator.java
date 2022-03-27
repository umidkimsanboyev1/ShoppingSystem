package uz.master.warehouse.validator.products;

import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.wareHouseProducts.WareHouseProductsCreateDto;
import uz.master.warehouse.dto.wareHouseProducts.WareHouseProductsUpdateDto;
import uz.master.warehouse.validator.BaseValidator;
import uz.master.warehouse.validator.GenericValidator;

import java.util.Objects;

@Component
public class WareHouseProductsValidator implements GenericValidator<WareHouseProductsCreateDto, WareHouseProductsUpdateDto> {
    @Override
    public boolean validForCreate(WareHouseProductsCreateDto createDto) {
        return Objects.nonNull(createDto.getProductId())
                && (createDto.getCount() != 0);
    }

    @Override
    public boolean validForUpdate(WareHouseProductsUpdateDto updateDto) {
        return Objects.nonNull(updateDto.getProductId());
    }
}
