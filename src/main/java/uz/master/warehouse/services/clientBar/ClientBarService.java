package uz.master.warehouse.services.clientBar;


import org.springframework.stereotype.Service;
import uz.master.warehouse.validator.ClientBarValidator;
import uz.master.warehouse.dto.clientBar.ClientBarCreateDto;
import uz.master.warehouse.dto.clientBar.ClientBarDto;
import uz.master.warehouse.dto.clientBar.ClientBarUpdateDto;
import uz.master.warehouse.entity.ClientBar;
import uz.master.warehouse.entity.Product;
import uz.master.warehouse.mapper.ClientBarMapper;
import uz.master.warehouse.repository.ClientBarRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;

import java.util.List;

@Service
public class ClientBarService extends AbstractService<ClientBarRepository, ClientBarMapper, ClientBarValidator> implements GenericCrudService<ClientBar, ClientBarDto, ClientBarCreateDto, ClientBarUpdateDto, Long> {

    public ClientBarService(ClientBarRepository repository, ClientBarMapper mapper, ClientBarValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public Long create(ClientBarCreateDto createDto) {
        List<Product> products = createDto.getProducts();
        double overAllPrice = products.stream().mapToDouble(Product::getPrice).sum();
        ClientBar bar = mapper.fromDto(createDto);
        bar.setOverAllPrice(overAllPrice);
        repository.save(bar);
        return null;
    }

    @Override
    public Void delete(Long id) {
        ClientBar byIdAndTakenFalse = repository.findByIdAndTakenFalse(id);
        byIdAndTakenFalse.setTaken(true);
        repository.save(byIdAndTakenFalse);
        return null;
    }

    @Override
    public Void update(ClientBarUpdateDto updateDto) {
        repository.save(mapper.fromUpdateDto(updateDto));
        return null;
    }

    @Override
    public List<ClientBarDto> getAll() {
        return mapper.toDto(repository.findAllByTakenFalse());
    }

    @Override
    public ClientBarDto get(Long id) {
        return mapper.toDto(repository.findByIdAndTakenFalse(id));
    }
}
