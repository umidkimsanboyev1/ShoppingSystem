package uz.master.warehouse.validator.organization;

import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.market.MarketCreateDto;
import uz.master.warehouse.dto.market.MarketUpdateDto;
import uz.master.warehouse.validator.GenericValidator;

import java.util.Objects;

@Component
public class MarketValidator implements GenericValidator<MarketCreateDto, MarketUpdateDto> {
    @Override
    public boolean validForCreate(MarketCreateDto createDto) {
        return (Objects.nonNull(createDto.getOrganizationId())
                && Objects.isNull(createDto.getName())
                && Objects.isNull(createDto.getLocation())
                && Objects.isNull(createDto.getOwnerId())
                && Objects.isNull(createDto.getDescription()));
    }

    @Override
    public boolean validForUpdate(MarketUpdateDto updateDto) {
        return (Objects.nonNull(updateDto.getName())
                && Objects.isNull(updateDto.getDescription())
                && Objects.isNull(updateDto.getLocation()));
    }
}
