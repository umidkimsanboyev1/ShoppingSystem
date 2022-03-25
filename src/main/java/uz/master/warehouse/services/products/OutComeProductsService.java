package uz.master.warehouse.services.products;

import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.outComeProducts.OutComeProductsCreateDto;
import uz.master.warehouse.dto.outComeProducts.OutComeProductsDto;
import uz.master.warehouse.dto.outComeProducts.OutComeProductsUpdateDto;
import uz.master.warehouse.entity.OutComeProducts;
import uz.master.warehouse.mapper.products.OutComeProductsMapper;
import uz.master.warehouse.repository.products.OutComeProductsRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;
import uz.master.warehouse.validator.products.OutComeProductsValidator;

import java.util.List;

@Service
public class OutComeProductsService   extends AbstractService<OutComeProductsRepository, OutComeProductsMapper, OutComeProductsValidator>
        implements GenericCrudService<OutComeProducts, OutComeProductsDto, OutComeProductsCreateDto, OutComeProductsUpdateDto, Long> {

    public OutComeProductsService(OutComeProductsRepository repository, OutComeProductsMapper mapper, OutComeProductsValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public Long create(OutComeProductsCreateDto createDto) {
        return null;
    }

    @Override
    public Void delete(Long id) {
        return null;
    }

    @Override
    public Void update(OutComeProductsUpdateDto updateDto) {
        return null;
    }

    @Override
    public List<OutComeProductsDto> getAll() {
        return null;
    }

    @Override
    public OutComeProductsDto get(Long id) {
        return null;
    }
}
