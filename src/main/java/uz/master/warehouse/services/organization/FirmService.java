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
        Firm firm = mapper.fromCreateDto(createDto);
        Long id = repository.save(firm).getId();
        return new DataDto<>(id);
    }

    @Override
    public DataDto<Void> delete(Long id) {
        repository.deleteFirm(id);
        return new DataDto<>();
    }

    @Override
    public DataDto<Long> update(FirmUpdateDto updateDto) {
        Firm firm = mapper.fromUpdateDto(updateDto);
        Long id = repository.save(firm).getId();
        return new DataDto<>(id);
    }

    @Override
    public DataDto<List<FirmDto>> getAll() {
        return new DataDto<>(mapper.toDto(repository.findAllByDeletedFalse()));
    }

    @Override
    public DataDto<FirmDto> get(Long id) {
        return new DataDto<>(mapper.toDto(repository.findByIdAndDeletedFalse(id)));
    }
}
