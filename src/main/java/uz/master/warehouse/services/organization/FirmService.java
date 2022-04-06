package uz.master.warehouse.services.organization;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.master.warehouse.criteria.BaseCriteria;
import uz.master.warehouse.criteria.GenericCriteria;
import uz.master.warehouse.dto.firm.FirmCreateDto;
import uz.master.warehouse.dto.firm.FirmDto;
import uz.master.warehouse.dto.firm.FirmUpdateDto;
import uz.master.warehouse.dto.responce.AppErrorDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.organization.Firm;
import uz.master.warehouse.mapper.organization.FirmMapper;
import uz.master.warehouse.repository.organization.FirmRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;
import uz.master.warehouse.validator.organization.FirmValidator;

import java.io.Serializable;
import java.util.List;

@Service
public class FirmService extends AbstractService<FirmRepository, FirmMapper> implements GenericCrudService<Firm, FirmDto, FirmCreateDto, FirmUpdateDto, GenericCriteria, Long> {
    public FirmService(FirmRepository repository, FirmMapper mapper) {
        super(repository, mapper);
    }


    @Override
    public DataDto<Long> create(FirmCreateDto createDto) {
        Firm firm = mapper.fromCreateDto(createDto);
        try{
            return new DataDto<>(repository.save(firm).getId());
        }catch (Exception e){
            return new DataDto<>(new AppErrorDto("NAME_ALREADY_TAKEN", HttpStatus.CONFLICT));
        }
    }

    @Override
    public DataDto<Void> delete(Long id) {
        if(!repository.existsById(id)){
            return new DataDto<>(new AppErrorDto("FIRM_NOT_FOUND", HttpStatus.NOT_FOUND));
        }
        repository.deleteFirm(id);
        return new DataDto<>();
    }

    @Override
    public DataDto<Long> update(FirmUpdateDto updateDto) {
        Firm firm = mapper.fromUpdateDto(updateDto);
        try{
            return new DataDto<>(repository.save(firm).getId());
        }catch (Exception e){
            return new DataDto<>(new AppErrorDto("NAME_ALREADY_TAKEN", HttpStatus.CONFLICT));
        }
    }

    @Override
    public DataDto<List<FirmDto>> getAll() {
        return new DataDto<>(mapper.toDto(repository.findAllByDeletedFalse()));
    }

    @Override
    public DataDto<FirmDto> get(Long id) {
        if(!repository.existsById(id)){
            return new DataDto<>(new AppErrorDto("FIRM_NOT_FOUND", HttpStatus.NOT_FOUND));
        }
        return new DataDto<>(mapper.toDto(repository.findByIdAndDeletedFalse(id)));
    }

    @Override
    public DataDto<List<FirmDto>> getWithCriteria(GenericCriteria criteria) {
        PageRequest request = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<FirmDto> firmDtoS = mapper.toDto(repository.findAllByDeletedFalse(request));
        return new DataDto<>(firmDtoS);
    }
}
