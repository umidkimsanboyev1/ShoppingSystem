package uz.master.warehouse.services.products;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.responce.AppErrorDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.dto.wareHouseProducts.WareHouseProductsCreateDto;
import uz.master.warehouse.dto.wareHouseProducts.WareHouseProductsDto;
import uz.master.warehouse.dto.wareHouseProducts.WareHouseProductsUpdateDto;
import uz.master.warehouse.entity.product.Product;
import uz.master.warehouse.entity.products.WareHouseProducts;
import uz.master.warehouse.mapper.products.WareHouseProductsMapper;
import uz.master.warehouse.repository.product.ProductRepository;
import uz.master.warehouse.repository.products.WareHouseProductsRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;
import uz.master.warehouse.validator.products.WareHouseProductsValidator;

import java.util.List;
import java.util.Optional;

@Service
public class WareHouseProductsService extends AbstractService<WareHouseProductsRepository, WareHouseProductsMapper, WareHouseProductsValidator>
        implements GenericCrudService<WareHouseProducts, WareHouseProductsDto, WareHouseProductsCreateDto, WareHouseProductsUpdateDto, Long> {

    private final ProductRepository productRepository;

    public WareHouseProductsService(WareHouseProductsRepository repository, WareHouseProductsMapper mapper, WareHouseProductsValidator validator, ProductRepository productRepository) {
        super(repository, mapper, validator);
        this.productRepository = productRepository;
    }


    @Override
    public DataDto<Long> create(WareHouseProductsCreateDto createDto) {
        Optional<Product> product = productRepository.findByIdAndDeletedFalse(createDto.getProductId());
        if (product.isEmpty()) {
            return new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, "Product not found", "product"));
        }
        WareHouseProducts warehouseProducts = mapper.fromCreateDto(createDto);
        return new DataDto<>(repository.save(warehouseProducts).getId());

    }

    @Override
    public DataDto<Void> delete(Long id) {
        repository.deleteWareHouseProductsById(id);
        return new DataDto<>();
    }

    @Override
    public DataDto<Long> update(WareHouseProductsUpdateDto updateDto) {
        Optional<WareHouseProducts> optional = repository.findById(updateDto.getId());
        if (optional.isEmpty()) {
            return new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, "Income Product not found", "product"));
        }
        WareHouseProducts income = mapper.fromUpdateDto(updateDto,optional.get());
        try {
            WareHouseProducts save = repository.save(income);
            return new DataDto<>(save.getId());
        } catch (Exception e) {
            return new DataDto<>(new AppErrorDto(HttpStatus.OK, "Bad credentional", "product"));

        }
    }

    @Override
    public DataDto<List<WareHouseProductsDto>> getAll() {
        List<WareHouseProducts> warehouseProductsList = repository.findAllByDeletedFalse();
        List<WareHouseProductsDto> warehouseProductsDto = mapper.toDto(warehouseProductsList);
        return new DataDto<>(warehouseProductsDto);
    }

    @Override
    public DataDto<WareHouseProductsDto> get(Long id) {
        Optional<WareHouseProducts> optional = repository.findByIdAndDeletedFalse(id);
        if (optional.isEmpty()) {
            return new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, "WareHouse Product not found", "product"));
        }
        WareHouseProductsDto warehouseProductsDto = mapper.toDto(optional.get());
        return new DataDto<>(warehouseProductsDto);
    }
}