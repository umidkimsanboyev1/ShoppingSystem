package uz.master.warehouse.services.products;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.responce.AppErrorDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.dto.wareHouseProducts.WareHouseProductsCreateDto;
import uz.master.warehouse.dto.wareHouseProducts.WareHouseProductsDto;
import uz.master.warehouse.dto.wareHouseProducts.WareHouseProductsUpdateDto;
import uz.master.warehouse.entity.base.Auditable;
import uz.master.warehouse.entity.product.Product;
import uz.master.warehouse.entity.products.InComeProducts;
import uz.master.warehouse.entity.products.OutComeProducts;
import uz.master.warehouse.entity.products.WareHouseProducts;
import uz.master.warehouse.mapper.products.WareHouseProductsMapper;
import uz.master.warehouse.repository.product.ProductRepository;
import uz.master.warehouse.repository.products.WareHouseProductsRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;
import uz.master.warehouse.validator.products.WareHouseProductsValidator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class WareHouseProductsService extends AbstractService<WareHouseProductsRepository, WareHouseProductsMapper>
        implements GenericCrudService<WareHouseProducts, WareHouseProductsDto, WareHouseProductsCreateDto, WareHouseProductsUpdateDto, Long> {

    private final ProductRepository productRepository;

    public WareHouseProductsService(WareHouseProductsRepository repository, WareHouseProductsMapper mapper, ProductRepository productRepository) {
        super(repository, mapper);
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
        WareHouseProducts income = mapper.fromUpdateDto(updateDto, optional.get());
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

    public WareHouseProducts getByProductId(Long id) {
        return repository.findByProductIdAndDeletedFalse(id);
    }

    public DataDto<List<WareHouseProductsDto>> getByModel(String model) {
        List<Product> products = productRepository.findAllByModelAndDeletedFalse(model);
        if (products.isEmpty()) {
            return new DataDto<>(new AppErrorDto("Model Not Found", HttpStatus.NOT_FOUND));
        }
        List<Long> productIds = products.stream().map(Auditable::getId).toList();
        List<WareHouseProducts> allByProductIdIsIn = repository.findByProductIdIn(productIds);
        return new DataDto<>(mapper.toDto(allByProductIdIsIn));
    }

    public DataDto<WareHouseProductsDto> getByModelAndColor(String model, String color) {
        Product product = productRepository.findByModelAndColorAndDeletedFalse(model, color);
        if (Objects.isNull(product)) {
            return new DataDto<>(new AppErrorDto("Model Not Found", HttpStatus.NOT_FOUND));
        }
        WareHouseProducts wareHouseProduct = this.getByProductId(product.getId());
        return new DataDto<>(mapper.toDto(wareHouseProduct));
    }


    public void checkCount(Long productId, int count) {
        repository.get(productId, count).orElseThrow(() -> {
            throw new RuntimeException("uzr");
        });
    }

    public void incomeProducts(InComeProducts list) {
        WareHouseProducts wareHouseProducts = this.getByProductId(list.getProductId());
        if (Objects.isNull(wareHouseProducts)) {
            this.create(new WareHouseProductsCreateDto(list.getCount(), list.getProductId()));
        } else
            this.update(new WareHouseProductsUpdateDto(wareHouseProducts.getCount() + list.getCount(), list.getProductId()));
    }

    public boolean outcomeProducts(OutComeProducts outComeProducts) {
        WareHouseProducts wareHouseProducts = this.getByProductId(outComeProducts.getProductId());
        if (Objects.nonNull(wareHouseProducts)) {
            if (wareHouseProducts.getCount() > outComeProducts.getCount()) {
                this.update(new WareHouseProductsUpdateDto(wareHouseProducts.getCount() - outComeProducts.getCount(), outComeProducts.getProductId()));
                return true;
            } else if (wareHouseProducts.getCount() == outComeProducts.getCount()) {
                this.delete(wareHouseProducts.getId());
                productRepository.deleteProduct(wareHouseProducts.getProductId());
                return true;
            } else
                return false;
        } else return false;
    }
}