package uz.master.warehouse.services.clientBar;


import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.master.warehouse.criteria.GenericCriteria;
import uz.master.warehouse.dto.clientBar.ClientBarCreateDto;
import uz.master.warehouse.dto.clientBar.ClientBarDto;
import uz.master.warehouse.dto.clientBar.ClientBarUpdateDto;
import uz.master.warehouse.dto.responce.AppErrorDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.clientBar.ClientBar;
import uz.master.warehouse.mapper.clientBar.ClientBarMapper;
import uz.master.warehouse.repository.clientBar.ClientBarRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;
import uz.master.warehouse.services.products.OutComeProductsService;
import uz.master.warehouse.session.SessionUser;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service

public class ClientBarService extends AbstractService<ClientBarRepository, ClientBarMapper> implements GenericCrudService<ClientBar, ClientBarDto, ClientBarCreateDto, ClientBarUpdateDto, GenericCriteria, Long> {


    public final OutComeProductsService service;
    public final SessionUser sessionUser;

    public ClientBarService(ClientBarRepository repository, ClientBarMapper mapper, OutComeProductsService service, SessionUser sessionUser) {
        super(repository, mapper);
        this.service = service;
        this.sessionUser = sessionUser;
    }


    @Transactional
    @Override
    public DataDto<Long> create(ClientBarCreateDto createDto) {
        try {
            return new DataDto<>(repository.save(mapper.fromDto(createDto)).getId());
        } catch (Exception e) {
            return new DataDto<>(new AppErrorDto("NAME_ALREADY_TAKEN", HttpStatus.CONFLICT));
        }
    }

    @Override
    public DataDto<Void> delete(Long id) {
        if (!repository.existsById(id)) {
            return new DataDto<>(new AppErrorDto("CLIENT_BAR_DTO", HttpStatus.NOT_FOUND));
        }
        repository.deleteClientBar(id);
        return new DataDto<>();
    }

    @Override
    public DataDto<Long> update(ClientBarUpdateDto updateDto) {
        ClientBar clientBar = mapper.fromUpdateDto(updateDto);
        try {
            return new DataDto<>(repository.save(clientBar).getId());
        } catch (Exception e) {
            return new DataDto<>(new AppErrorDto("NAME_ALREADY_TAKEN", HttpStatus.CONFLICT));
        }
    }

    @Override
    public DataDto<List<ClientBarDto>> getAll() {
        return new DataDto<>(mapper.toDto(repository.findAllByDeletedFalse()));
    }

    @Override
    public DataDto<ClientBarDto> get(Long id) {
        if (!repository.existsById(id)) {
            return new DataDto<>(new AppErrorDto("CLIENT_BAR_DTO", HttpStatus.NOT_FOUND));
        }
        return new DataDto<>(mapper.toDto(repository.findByIdAndDeletedFalse(id)));
    }

    @Override
    public DataDto<List<ClientBarDto>> getWithCriteria(GenericCriteria criteria) {
        PageRequest pageRequest = PageRequest.of(criteria.getPage(), criteria.getSize());
        return new DataDto<>(mapper.toDto(repository.findAllByDeletedFalse(pageRequest).stream().toList()));
    }

    public DataDto<List<ClientBarDto>> getByOrgId() {
        return new DataDto<>(
                mapper.toDto(
                        repository.findAllByOrgIdAndDeletedFalseAndTakenFalseOrderByCreatedAtDesc(
                                sessionUser.getOrgId())));
    }


    public DataDto<List<ClientBarDto>> getThisDay() {
        List<ClientBar> all = repository.findAllByOrgIdAndTakenTrueAndCreatedAtAndDeletedFalse(/*sessionUser.getOrgId()*/1L);
        List<ClientBarDto> clientBarDtos = mapper.toDto(all);
        return new DataDto<>(clientBarDtos);
    }
}
