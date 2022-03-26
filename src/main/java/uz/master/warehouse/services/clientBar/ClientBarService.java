package uz.master.warehouse.services.clientBar;


import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.clientBar.ClientBarCreateDto;
import uz.master.warehouse.dto.clientBar.ClientBarDto;
import uz.master.warehouse.dto.clientBar.ClientBarUpdateDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.clientBar.ClientBar;
import uz.master.warehouse.mapper.clientBar.ClientBarMapper;
import uz.master.warehouse.repository.clientBar.ClientBarRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;
import uz.master.warehouse.validator.clientBar.ClientBarValidator;

import javax.xml.crypto.Data;
import java.util.List;

@Service
public class ClientBarService extends AbstractService<ClientBarRepository, ClientBarMapper, ClientBarValidator> implements GenericCrudService<ClientBar, ClientBarDto, ClientBarCreateDto, ClientBarUpdateDto, Long> {

    public ClientBarService(ClientBarRepository repository, ClientBarMapper mapper, ClientBarValidator validator) {
        super(repository, mapper, validator);
    }


    @Override
    public DataDto<Long> create(ClientBarCreateDto createDto) {
        return new DataDto<>(repository.save(mapper.fromCreateDto(createDto)).getId());
    }

    @Override
    public DataDto<Void> delete(Long id) {
        repository.deleteClientBar(id);
        return new DataDto<>();
    }

    @Override
    public DataDto<Long> update(ClientBarUpdateDto updateDto) {
        ClientBar clientBar = mapper.fromUpdateDto(updateDto);
        Long id = repository.save(clientBar).getId();
        return new DataDto<>(id);
    }

    @Override
    public DataDto<List<ClientBarDto>> getAll() {
        return new DataDto<>(mapper.toDto(repository.findAllByDeletedFalse()));
    }

    @Override
    public DataDto<ClientBarDto> get(Long id) {
        return new DataDto<>(mapper.toDto(repository.findByIdAndDeletedFalse(id)));
    }
}
