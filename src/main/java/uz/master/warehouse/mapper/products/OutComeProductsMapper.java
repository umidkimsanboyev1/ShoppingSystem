package uz.master.warehouse.mapper.products;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.outComeProducts.OutComeProductsCreateDto;
import uz.master.warehouse.dto.outComeProducts.OutComeProductsDto;
import uz.master.warehouse.dto.outComeProducts.OutComeProductsUpdateDto;
import uz.master.warehouse.entity.products.OutComeProducts;
import uz.master.warehouse.mapper.BaseMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface OutComeProductsMapper extends BaseMapper<OutComeProducts, OutComeProductsDto, OutComeProductsCreateDto, OutComeProductsUpdateDto> {
    @Override
    OutComeProductsDto toDto(OutComeProducts inComeProducts);

    @Override
    List<OutComeProductsDto> toDto(List<OutComeProducts> e);

    @Override
    OutComeProducts fromCreateDto(OutComeProductsCreateDto inComeProductsCreateDto);

    @Override
    OutComeProducts fromUpdateDto(OutComeProductsUpdateDto inComeProductsUpdateDto);


    void updateModel(OutComeProductsUpdateDto dto, @MappingTarget OutComeProducts products);
}