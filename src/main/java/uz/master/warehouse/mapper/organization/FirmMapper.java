package uz.master.warehouse.mapper.organization;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.firm.FirmCreateDto;
import uz.master.warehouse.dto.firm.FirmDto;
import uz.master.warehouse.dto.firm.FirmUpdateDto;
import uz.master.warehouse.entity.organization.Firm;
import uz.master.warehouse.mapper.BaseMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface FirmMapper extends BaseMapper<Firm,FirmDto,FirmCreateDto, FirmUpdateDto> {
    Firm create(FirmCreateDto dto);

    @Override
    List<FirmDto> toDto(List<Firm> e);

    @Override
    Firm fromCreateDto(FirmCreateDto firmCreateDto);

    @Override
    Firm fromUpdateDto(FirmUpdateDto firmUpdateDto);

    FirmDto toDto(Firm firm);


}
