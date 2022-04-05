package uz.master.warehouse.services.wareHouse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import uz.master.warehouse.criteria.GenericCriteria;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.exception.NotFoundException;
import uz.master.warehouse.validator.warehouse.WareHouseValidator;
import uz.master.warehouse.dto.wareHouse.WareHouseCreateDto;
import uz.master.warehouse.dto.wareHouse.WareHouseDto;
import uz.master.warehouse.dto.wareHouse.WareHouseUpdateDto;
import uz.master.warehouse.entity.wareHouse.WareHouse;
import uz.master.warehouse.mapper.wareHouse.WareHouseMapper;
import uz.master.warehouse.repository.wareHouse.WareHouseRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;

import java.util.List;

@Service
public class WareHouseService extends AbstractService<
        WareHouseRepository,
        WareHouseMapper
        > implements GenericCrudService<
        WareHouse,
        WareHouseDto,
        WareHouseCreateDto,
        WareHouseUpdateDto,
        Long
        > {
    public WareHouseService(WareHouseRepository repository, @Qualifier("wareHouseMapperImpl") WareHouseMapper mapper) {
        super(repository, mapper);
    }


    @Override
    public DataDto<Long> create(WareHouseCreateDto createDto) {
        WareHouse wareHouse = mapper.fromCreateDto(createDto);
        Long id = repository.save(wareHouse).getId();
        return new DataDto<>(id);
    }

    @Override
    public DataDto<Void> delete(Long id) {
        repository.deleteId(id);
        return new DataDto<>();
    }

    @Override
    public DataDto<Long> update(WareHouseUpdateDto updateDto) {
        repository.update(updateDto.getName(),updateDto.getLocation(),updateDto.getId());
        return new DataDto<>(updateDto.getId());
    }

    @Override
    public DataDto<List<WareHouseDto>> getAll() {
        List<WareHouse> allByDeletedFalse = repository.findAllByDeletedFalse();
        List<WareHouseDto> wareHouse = mapper.toDto(allByDeletedFalse);
        return new DataDto<>(wareHouse);
    }

    @Override
    public DataDto<WareHouseDto> get(Long id) {
        WareHouse found = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("not found");
        });
        return new DataDto<>(mapper.toDto(found));
    }

    @Override
    public DataDto<List<WareHouseDto>> getWithCriteria(GenericCriteria criteria) {
        return null;
    }
}
