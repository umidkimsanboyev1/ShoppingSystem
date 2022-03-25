package uz.master.warehouse.mapper.products;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.master.warehouse.mapper.BaseMapper;

@Component
@Mapper(componentModel = "spring")
public interface WareHouseProductsMapper extends BaseMapper {
}
