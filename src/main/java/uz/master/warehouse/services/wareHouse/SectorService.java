package uz.master.warehouse.services.wareHouse;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.dto.sector.SectorCreateDto;
import uz.master.warehouse.dto.sector.SectorDto;
import uz.master.warehouse.dto.sector.SectorUpdateDto;
import uz.master.warehouse.entity.wareHouse.Sector;
import uz.master.warehouse.mapper.wareHouse.SectorMapper;
import uz.master.warehouse.repository.wareHouse.SectorRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;

import java.util.List;

@Service
public class SectorService extends AbstractService<SectorRepository, SectorMapper>
        implements GenericCrudService<
        Sector,
        SectorDto,
        SectorCreateDto,
        SectorUpdateDto,
        Long> {


    public SectorService(SectorRepository repository, SectorMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public DataDto<Long> create(SectorCreateDto createDto) {
        Sector sector = mapper.fromCreateDto(createDto);
        sector.setName(createDto.getName());
        sector.setColor(createDto.getColor());
        sector.setWareHouseId(createDto.getWareHouseId());
        Sector save = repository.save(sector);
        return new DataDto<>(save.getId());
    }

    @Override
    public DataDto<Void> delete(Long id) {
        repository.deleteSector(id);
        return new DataDto<>();
    }

    @Override
    public DataDto<Long> update(SectorUpdateDto updateDto) {
        Sector sector = mapper.fromUpdateDto(updateDto);
        sector.setName(updateDto.getName());
        sector.setColor(updateDto.getColor());
        sector.setWareHouseId(updateDto.getWareHouseId());
        Sector save = repository.save(sector);
        return new DataDto<>(save.getId());
    }


    @Override
    public DataDto<List<SectorDto>> getAll() {
        List<Sector> list = repository.findAll();
        return new DataDto<>(mapper.toDto(list));
    }

    @Override
    public DataDto<SectorDto> get(Long id) {
        Sector sector = repository.findById(id).orElseThrow(() -> {
            throw new UsernameNotFoundException("Not found");
        });
        return new DataDto<>(mapper.toDto(sector));
    }
}
