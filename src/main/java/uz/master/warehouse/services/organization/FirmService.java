package uz.master.warehouse.services.organization;

import uz.master.warehouse.dto.firm.FirmCreateDto;
import uz.master.warehouse.dto.firm.FirmDto;
import uz.master.warehouse.dto.firm.FirmUpdateDto;
import uz.master.warehouse.entity.Firm;
import uz.master.warehouse.mapper.FirmMapper;
import uz.master.warehouse.repository.FirmRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;
import uz.master.warehouse.validator.FirmValidator;

import java.util.List;

public class FirmService extends AbstractService<FirmRepository, FirmMapper, FirmValidator>  implements GenericCrudService<Firm, FirmDto, FirmCreateDto, FirmUpdateDto,Long> {
    public FirmService(FirmRepository repository, FirmMapper mapper, FirmValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public Long create(FirmCreateDto createDto) {
        return null;
    }

    @Override
    public Void delete(Long id) {
        return null;
    }

    @Override
    public Void update(FirmUpdateDto updateDto) {
        return null;
    }

    @Override
    public List<FirmDto> getAll() {
        return null;
    }

    @Override
    public FirmDto get(Long id) {
        return null;
    }
}
