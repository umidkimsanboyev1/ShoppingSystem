package uz.master.warehouse.services.wareHouse;

import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.responce.DataDto;
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
    public DataDto<Long> create(WareHouseCreateDto createDto) {
        return null;
    }

    @Override
    public DataDto<Void> delete(Long id) {
        return new DataDto<>();
    }

    @Override
    public DataDto<Long> update(WareHouseUpdateDto updateDto) {
        return null;
    }

    @Override
    public DataDto<List<WareHouseDto>> getAll() {
        return null;
    }

    @Override
    public DataDto<WareHouseDto> get(Long id) {
        return null;
    }
}
