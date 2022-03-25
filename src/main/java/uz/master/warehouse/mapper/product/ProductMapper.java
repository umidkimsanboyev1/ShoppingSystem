package uz.master.warehouse.mapper.product;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.product.ProductCreateDto;
import uz.master.warehouse.dto.product.ProductDto;
import uz.master.warehouse.dto.product.ProductUpdateDto;
import uz.master.warehouse.entity.product.Product;
import uz.master.warehouse.mapper.BaseMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<Product, ProductDto, ProductCreateDto, ProductUpdateDto>
{
    @Override
    ProductDto toDto(Product product);

    @Override
    List<ProductDto> toDto(List<Product> e);

    @Override
    Product fromCreateDto(ProductCreateDto productCreateDto);

    @Override
    Product fromUpdateDto(ProductUpdateDto productUpdateDto);
}
