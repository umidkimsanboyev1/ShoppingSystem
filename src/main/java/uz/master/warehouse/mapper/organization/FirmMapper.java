package uz.master.warehouse.mapper.organization;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.firm.FirmCreateDto;
import uz.master.warehouse.dto.firm.FirmDto;
import uz.master.warehouse.entity.organization.Firm;
import uz.master.warehouse.mapper.BaseMapper;

@Component
@Mapper(componentModel = "spring")
public interface FirmMapper extends BaseMapper {
    Firm create(FirmCreateDto dto);

    FirmDto toDto(Firm firm);


}
