package uz.master.warehouse.mapper.wareHouse;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.sector.SectorCreateDto;
import uz.master.warehouse.dto.sector.SectorDto;
import uz.master.warehouse.dto.sector.SectorUpdateDto;
import uz.master.warehouse.entity.wareHouse.Sector;
import uz.master.warehouse.mapper.BaseMapper;

@Component
@Mapper(componentModel = "spring")
public interface SectorMapper extends BaseMapper<Sector, SectorDto, SectorCreateDto, SectorUpdateDto> {

}
