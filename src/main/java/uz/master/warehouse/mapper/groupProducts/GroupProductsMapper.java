package uz.master.warehouse.mapper.groupProducts;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.groupProducts.GroupProductsCreateDto;
import uz.master.warehouse.dto.groupProducts.GroupProductsDto;
import uz.master.warehouse.dto.groupProducts.GroupProductsUpdateDto;
import uz.master.warehouse.entity.product.GroupProducts;
import uz.master.warehouse.mapper.BaseMapper;

import java.util.List;

/**
 * @author Panjiyev Javohir, сб 14:58. 26.03.2022
 */
@Component
@Mapper(componentModel = "spring")
public interface GroupProductsMapper extends BaseMapper<GroupProducts, GroupProductsDto, GroupProductsCreateDto, GroupProductsUpdateDto> {

    @Override
    GroupProductsDto toDto(GroupProducts groupProducts);

    @Override
    List<GroupProductsDto> toDto(List<GroupProducts> e);

    @Override
    GroupProducts fromCreateDto(GroupProductsCreateDto groupProductsCreateDto);

    @Override
    GroupProducts fromUpdateDto(GroupProductsUpdateDto groupProductsUpdateDto);
}
