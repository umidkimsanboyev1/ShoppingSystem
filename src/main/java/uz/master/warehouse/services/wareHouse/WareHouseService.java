package uz.master.warehouse.services.wareHouse;

import org.springframework.stereotype.Service;
import uz.master.warehouse.validator.WareHouseValidator;
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
        WareHouseMapper,
        WareHouseValidator
        > implements GenericCrudService<
        WareHouse,
        WareHouseDto,
        WareHouseCreateDto,
        WareHouseUpdateDto,
        Long
        > {
    public WareHouseService(WareHouseRepository repository, WareHouseMapper mapper, WareHouseValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public Long create(WareHouseCreateDto createDto) {
        return null;
    }

    @Override
    public Void delete(Long id) {
        return null;
    }

    @Override
    public Void update(WareHouseUpdateDto updateDto) {
        return null;
    }

    @Override
    public List<WareHouseDto> getAll() {
        return null;
    }

    @Override
    public WareHouseDto get(Long id) {
        return null;
    }
}
