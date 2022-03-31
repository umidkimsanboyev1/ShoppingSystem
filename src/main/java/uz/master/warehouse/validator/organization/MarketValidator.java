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
                && Objects.nonNull(createDto.getName())
                && Objects.nonNull(createDto.getLocation())
                && Objects.nonNull(createDto.getOwnerId())
                && Objects.nonNull(createDto.getDescription()));
    }

    @Override
    public boolean validForUpdate(MarketUpdateDto updateDto) {
        return (Objects.nonNull(updateDto.getName())
                && Objects.nonNull(updateDto.getDescription())
                && Objects.nonNull(updateDto.getLocation()));
    }
}
