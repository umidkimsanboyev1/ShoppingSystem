package uz.master.warehouse.services.product;

import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.master.warehouse.criteria.GenericCriteria;
import uz.master.warehouse.criteria.ProductCriteria;
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
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class ProductService extends AbstractService<ProductRepository, ProductMapper> implements GenericCrudService<Product, ProductDto, ProductCreateDto, ProductUpdateDto, ProductCriteria, Long> {

    private final FirmService firmService;
    private final EntityManager entityManager;
    @PersistenceContext
    public EntityManager em;

    public ProductService(
            ProductRepository repository,
            ProductMapper mapper, FirmService firmService, EntityManager manager
    ) {
        super(repository, mapper);
        this.firmService = firmService;
        this.entityManager = manager;
    }

    @Override
    public DataDto<Long> create(ProductCreateDto createDto) {

        if (Objects.isNull(firmService.get(createDto.getFirmId()).getData())) {
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

        Product product = mapper.fromUpdateDto(updateDto);
        product.setDefault_price(updateDto.getDefault_price());
        Product save = repository.save(product);
        return new DataDto<>(save.getId());
    }

    @Override
    public DataDto<List<ProductDto>> getAll() {
        return new DataDto<>(mapper.toDto(repository.findAllByDeletedFalse()));
    }

    @Override
    public DataDto<ProductDto> get(Long id) {
        Product product = repository.findByIdAndDeletedFalse(id).orElseThrow(() -> {
            throw new UsernameNotFoundException("Not found");
        });
        return new DataDto<>(mapper.toDto(product));
    }

    @Override
    public DataDto<List<ProductDto>> getWithCriteria(ProductCriteria criteria) {

        List<Product> products;
        StringBuilder builder = new StringBuilder();
        builder.append("select * from table where ");

        if (Objects.nonNull(criteria.getModel())) {
            builder.append("model = %s ".formatted(criteria.getModel()));
        }

        if (Objects.nonNull(criteria.getFirmId())) {
            builder.append("firm_id = firm_id ");
        }
        if (Objects.nonNull(criteria.getColor())) {
            builder.append("AndColor");
        }



//        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
//        if (Objects.nonNull(criteria.getModel())) {
//            products = repository.findAllByModelAndDeletedFalse(criteria.getModel(), pageable).stream().toList();
////            if (Objects.nonNull(criteria.getColor())) {
////                List<Product> products1 = products.stream().filter(product ->
////                        product.getColor().equalsIgnoreCase(criteria.getColor())
////                ).toList();
////            }
////            if (Objects.nonNull(criteria.getFirmId())) {
////                List<Product> products1 = products.stream().filter(product ->
////                        product.getFirmId().equalsIgnoreCase(criteria.getColor())
////                ).toList();
////            }
//        } else if (Objects.nonNull(criteria.getColor())) {
//            products = repository.findAllByColorAndDeletedFalse(criteria.getColor(), pageable).stream().toList();
//        } else if (Objects.nonNull(criteria.getFirmId())) {
//            products = repository.findAllByFirmIdAndDeletedFalse(criteria.getFirmId(), pageable).stream().toList();
//        } else {
//            products = repository.findAllByDeletedFalse();
//        }
        return new DataDto<>(mapper.toDto(products));
    }


    @Transactional
    public DataDto<List<ProductDto>> search(String name) {
        SearchSession searchSession = Search.session(entityManager);
        SearchResult<Product> result = searchSession.search(Product.class)
                .where(f -> f.match()
                        .fields("model")
                        .matching(name))
                .fetch(20);

        long totalHitCount = result.total().hitCount();
        List<ProductDto> productDtos = mapper.toDto(result.hits());
        return new DataDto<>(productDtos, totalHitCount);
    }

    public DataDto<List<ProductDto>> getByFirmId(Long id) {
        return new DataDto<>(mapper.toDto(repository.findAllByDeletedFalseAndFirmId(id)));


    }
}
