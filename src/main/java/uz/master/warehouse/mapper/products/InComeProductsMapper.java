package uz.master.warehouse.mapper.products;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.InComeProducts.InComeProductsCreateDto;
import uz.master.warehouse.dto.InComeProducts.InComeProductsDto;
import uz.master.warehouse.dto.InComeProducts.InComeProductsUpdateDto;
import uz.master.warehouse.entity.products.InComeProducts;
import uz.master.warehouse.mapper.BaseMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface InComeProductsMapper extends BaseMapper<InComeProducts, InComeProductsDto, InComeProductsCreateDto, InComeProductsUpdateDto> {
    @Override
    InComeProductsDto toDto(InComeProducts inComeProducts);

    @Override
    List<InComeProductsDto> toDto(List<InComeProducts> e);

    @Override
    InComeProducts fromCreateDto(InComeProductsCreateDto inComeProductsCreateDto);

    @Override
    InComeProducts fromUpdateDto(InComeProductsUpdateDto inComeProductsUpdateDto);
}
