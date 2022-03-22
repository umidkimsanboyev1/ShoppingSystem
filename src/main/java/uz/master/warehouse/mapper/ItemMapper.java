package uz.master.warehouse.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.item.ItemCreateDto;
import uz.master.warehouse.dto.item.ItemDto;
import uz.master.warehouse.entity.Item;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ItemMapper extends BaseMapper {
    Item fromDto(ItemCreateDto createDto);

    List<ItemDto> toDto(List<Item> allByDeletedFalse);

    ItemDto toDto(Item byId);
}
