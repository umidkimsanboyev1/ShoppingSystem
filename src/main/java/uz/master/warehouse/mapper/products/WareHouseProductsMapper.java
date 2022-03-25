package uz.master.warehouse.mapper.products;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.wareHouseProducts.WareHouseProductsCreateDto;
import uz.master.warehouse.dto.wareHouseProducts.WareHouseProductsDto;
import uz.master.warehouse.dto.wareHouseProducts.WareHouseProductsUpdateDto;
import uz.master.warehouse.entity.products.WareHouseProducts;
import uz.master.warehouse.mapper.BaseMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface WareHouseProductsMapper extends BaseMapper<WareHouseProducts, WareHouseProductsDto, WareHouseProductsCreateDto, WareHouseProductsUpdateDto> {

    @Override
    WareHouseProductsDto toDto(WareHouseProducts wareHouseProducts);

    @Override
    List<WareHouseProductsDto> toDto(List<WareHouseProducts> e);

    @Override
    WareHouseProducts fromCreateDto(WareHouseProductsCreateDto wareHouseProductsCreateDto);

    @Override
    WareHouseProducts fromUpdateDto(WareHouseProductsUpdateDto wareHouseProductsUpdateDto);
}
