package uz.master.warehouse.validator.warehouse;

import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.sector.SectorCreateDto;
import uz.master.warehouse.dto.sector.SectorUpdateDto;
import uz.master.warehouse.validator.GenericValidator;

@Component
public class SectorValidator implements GenericValidator<SectorCreateDto, SectorUpdateDto> {
    @Override
    public boolean validForCreate(SectorCreateDto createDto) {
        return false;
    }

    @Override
    public boolean validForUpdate(SectorUpdateDto updateDto) {
        return false;
    }
}
