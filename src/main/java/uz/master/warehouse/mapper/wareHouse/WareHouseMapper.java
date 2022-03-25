package uz.master.warehouse.mapper.wareHouse;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.wareHouse.WareHouseCreateDto;
import uz.master.warehouse.dto.wareHouse.WareHouseDto;
import uz.master.warehouse.dto.wareHouse.WareHouseUpdateDto;
import uz.master.warehouse.entity.wareHouse.WareHouse;
import uz.master.warehouse.mapper.BaseMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface WareHouseMapper extends BaseMapper<WareHouse, WareHouseDto, WareHouseCreateDto, WareHouseUpdateDto> {

    @Override
    WareHouseDto toDto(WareHouse wareHouse);

    @Override
    List<WareHouseDto> toDto(List<WareHouse> e);

    @Override
    WareHouse fromCreateDto(WareHouseCreateDto wareHouseCreateDto);

    @Override
    WareHouse fromUpdateDto(WareHouseUpdateDto wareHouseUpdateDto);
}