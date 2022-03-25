package uz.master.warehouse.mapper.product;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.master.warehouse.mapper.BaseMapper;

@Component
@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper {
}
