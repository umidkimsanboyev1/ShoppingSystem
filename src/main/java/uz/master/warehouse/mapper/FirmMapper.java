package uz.master.warehouse.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.firm.FirmCreateDto;
import uz.master.warehouse.dto.firm.FirmDto;
import uz.master.warehouse.entity.Firm;

@Component
@Mapper(componentModel = "spring")
public interface FirmMapper extends BaseMapper{
    Firm create(FirmCreateDto dto);

    FirmDto toDto(Firm firm);


}
