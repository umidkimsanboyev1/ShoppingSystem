package uz.master.warehouse.mapper.organization;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.market.MarketCreateDto;
import uz.master.warehouse.dto.market.MarketDto;
import uz.master.warehouse.dto.market.MarketUpdateDto;
import uz.master.warehouse.entity.organization.Market;
import uz.master.warehouse.mapper.BaseMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface MarketMapper extends BaseMapper {
    Market fromCreateDto(MarketCreateDto createDto);

    Market fromUpdateDto(MarketUpdateDto updateDto);

    List<MarketDto> toDto(List<Market> all);

    MarketDto toDto(Market market);
}
