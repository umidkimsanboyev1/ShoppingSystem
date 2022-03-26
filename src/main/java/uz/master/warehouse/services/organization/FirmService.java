package uz.master.warehouse.services.organization;

import uz.master.warehouse.dto.firm.FirmCreateDto;
import uz.master.warehouse.dto.firm.FirmDto;
import uz.master.warehouse.dto.firm.FirmUpdateDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.organization.Firm;
import uz.master.warehouse.mapper.organization.FirmMapper;
import uz.master.warehouse.repository.organization.FirmRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;
import uz.master.warehouse.validator.organization.FirmValidator;

import java.util.List;

public class FirmService extends AbstractService<FirmRepository, FirmMapper, FirmValidator>  implements GenericCrudService<Firm, FirmDto, FirmCreateDto, FirmUpdateDto,Long> {
    public FirmService(FirmRepository repository, FirmMapper mapper, FirmValidator validator) {
        super(repository, mapper, validator);
    }


    @Override
    public DataDto<Long> create(FirmCreateDto createDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public DataDto<Long> update(FirmUpdateDto updateDto) {
        return null;
    }

    @Override
    public DataDto<List<FirmDto>> getAll() {
        return null;
    }

    @Override
    public DataDto<FirmDto> get(Long id) {
        return null;
    }
}
