package uz.master.warehouse.services.products;

import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.InComeProducts.InComeProductsCreateDto;
import uz.master.warehouse.dto.InComeProducts.InComeProductsDto;
import uz.master.warehouse.dto.InComeProducts.InComeProductsUpdateDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.products.InComeProducts;
import uz.master.warehouse.mapper.products.InComeProductsMapper;
import uz.master.warehouse.repository.products.InComeProductsRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;
import uz.master.warehouse.validator.products.InComeProductsValidator;

import java.util.List;

@Service
public class InComeProductsService extends AbstractService<InComeProductsRepository, InComeProductsMapper, InComeProductsValidator>
        implements GenericCrudService<InComeProducts, InComeProductsDto, InComeProductsCreateDto, InComeProductsUpdateDto, Long> {

    public InComeProductsService(InComeProductsRepository repository, InComeProductsMapper mapper, InComeProductsValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public DataDto<Long> create(InComeProductsCreateDto createDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public DataDto<Long> update(InComeProductsUpdateDto updateDto) {
        return null;
    }

    @Override
    public DataDto<List<InComeProductsDto>> getAll() {
        return null;
    }

    @Override
    public DataDto<InComeProductsDto> get(Long id) {
        return null;
    }
}
