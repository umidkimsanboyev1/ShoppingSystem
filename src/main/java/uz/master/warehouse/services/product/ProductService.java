package uz.master.warehouse.services.product;

import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.product.ProductCreateDto;
import uz.master.warehouse.dto.product.ProductDto;
import uz.master.warehouse.dto.product.ProductUpdateDto;
import uz.master.warehouse.dto.responce.AppErrorDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.product.Product;
import uz.master.warehouse.mapper.product.ProductMapper;
import uz.master.warehouse.repository.product.ProductRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;
import uz.master.warehouse.services.organization.FirmService;
import uz.master.warehouse.validator.product.ProductValidator;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService extends AbstractService<ProductRepository, ProductMapper, ProductValidator> implements GenericCrudService<Product, ProductDto, ProductCreateDto, ProductUpdateDto, Long> {

    private final FirmService firmService;
    private final EntityManager entityManager;

    public ProductService(ProductRepository repository,
                          ProductMapper mapper,
                          ProductValidator validator, FirmService firmService, EntityManager manager) {
        super(repository, mapper, validator);
        this.firmService = firmService;
        this.entityManager = manager;
    }

    @Override
    public DataDto<Long> create(@Valid ProductCreateDto createDto) {
        if (!validator.validForCreate(createDto)) {
            return new DataDto<>(new AppErrorDto("Not Valid On Create", HttpStatus.CONFLICT));
        }
        if (Objects.isNull(firmService.get(createDto.getFirmId()    ).getData())) {
            return new DataDto<>(new AppErrorDto("Firm Not Found", HttpStatus.NOT_FOUND));
        }
        Product product = mapper.fromCreateDto(createDto);
        product.setColor(createDto.getColor());
        product.setDefault_price(createDto.getDefault_price());
        product.setModel(createDto.getModel());
        product.setFirmId(createDto.getFirmId());
        product.setItem_count(createDto.getItem_count());
        Product save = repository.save(product);
        return new DataDto<>(save.getId());
    }

    @Override
    public DataDto<Void> delete(Long id) {
        repository.deleteProduct(id);
        return new DataDto<>();
    }

    @Override
    public DataDto<Long> update(ProductUpdateDto updateDto) {
        if (!validator.validForUpdate(updateDto)) {
            return new DataDto<>(new AppErrorDto("Not Valid On Create", HttpStatus.CONFLICT));
        }
        Product product = mapper.fromUpdateDto(updateDto);
        product.setDefault_price(updateDto.getDefault_price());
        Product save = repository.save(product);
        return new DataDto<>(save.getId());
    }

    @Override
    public DataDto<List<ProductDto>> getAll() {
        List<Product> list = repository.findAllByDeletedFalse();
        return new DataDto<>(mapper.toDto(list));
    }

    @Override
    public DataDto<ProductDto> get(Long id) {
        Product product = repository.findByIdAndDeletedFalse(id).orElseThrow(() -> {
            throw new UsernameNotFoundException("Not found");
        });
        return new DataDto<>(mapper.toDto(product));
    }


    @Transactional
    public DataDto<List<ProductDto>>search(String name){
        SearchSession searchSession = Search.session( entityManager );



        SearchResult<Product> result = searchSession.search( Product.class )
                .where( f -> f.match()
                        .fields( "model")
                        .matching( name ) )
                .fetch( 20 );

        long totalHitCount = result.total().hitCount();
        List<ProductDto> productDtos = mapper.toDto(result.hits());
        return new DataDto<>(productDtos,totalHitCount);
    }

}
