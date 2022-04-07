package uz.master.warehouse.mapper.wareHouse;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.sector.SectorCreateDto;
import uz.master.warehouse.dto.sector.SectorDto;
import uz.master.warehouse.dto.sector.SectorUpdateDto;
import uz.master.warehouse.entity.wareHouse.Sector;
import uz.master.warehouse.mapper.BaseMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface SectorMapper extends BaseMapper<Sector, SectorDto, SectorCreateDto, SectorUpdateDto> {
    @Override
    SectorDto toDto(Sector sector);

    @Override
    List<SectorDto> toDto(List<Sector> e);

    @Override
    Sector fromCreateDto(SectorCreateDto sectorCreateDto);

    @Override
    Sector fromUpdateDto(SectorUpdateDto sectorUpdateDto);

    Sector fromUpdateDto(SectorUpdateDto sectorUpdateDto, @MappingTarget Sector sector);
}
