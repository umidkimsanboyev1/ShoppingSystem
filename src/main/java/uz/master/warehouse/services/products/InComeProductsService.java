package uz.master.warehouse.services.products;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.InComeProducts.InComeProductsCreateDto;
import uz.master.warehouse.dto.InComeProducts.InComeProductsDto;
import uz.master.warehouse.dto.InComeProducts.InComeProductsUpdateDto;
import uz.master.warehouse.dto.responce.AppErrorDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.products.InComeProducts;
import uz.master.warehouse.mapper.products.InComeProductsMapper;
import uz.master.warehouse.repository.product.GroupProductsRepository;
import uz.master.warehouse.repository.product.ProductRepository;
import uz.master.warehouse.repository.products.InComeProductsRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;
import uz.master.warehouse.validator.products.InComeProductsValidator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
public class InComeProductsService extends AbstractService<InComeProductsRepository, InComeProductsMapper, InComeProductsValidator>
        implements GenericCrudService<InComeProducts, InComeProductsDto, InComeProductsCreateDto, InComeProductsUpdateDto, Long> {

    public InComeProductsService(InComeProductsRepository repository, InComeProductsMapper mapper, InComeProductsValidator validator, ProductRepository productRepository, GroupProductsRepository groupProductsRepository) {
        super( repository, mapper, validator );
        this.productRepository = productRepository;
        this.groupProductsRepository = groupProductsRepository;
    }

    private final ProductRepository productRepository;
    private final GroupProductsRepository groupProductsRepository;

    @Override
    public DataDto<Long> create(InComeProductsCreateDto createDto) {
        int existsProduct = productRepository.existsByProduct( createDto.getProductId() );
        if (existsProduct < 1) {
            return new DataDto<>( new AppErrorDto( HttpStatus.OK, "product not found", "product" ) );
        }
        int existsGroupProduct = groupProductsRepository.existsGroupProduct( createDto.getProductId() );
        if (existsGroupProduct < 1) {
            return new DataDto<>( new AppErrorDto( HttpStatus.OK, "Group Product not found", "product" ) );
        }
        InComeProducts inComeProducts = mapper.fromCreateDto( createDto );

        return new DataDto<>( repository.save( inComeProducts ).getId() );

    }

    @Override
    public DataDto<Void> delete(Long id) {


        repository.deleteById( id );
        return new DataDto<>();
    }

    @Override
    public DataDto<Long> update(InComeProductsUpdateDto updateDto) {
        Optional<InComeProducts> optional = repository.findById( updateDto.getId() );
        if (optional.isEmpty()) {

            return new DataDto<>( new AppErrorDto( HttpStatus.OK, "Income Product not found", "product" ) );
        }
        InComeProducts income = mapper.fromUpdateDto( updateDto, optional.get() );
        try {

            InComeProducts save = repository.save( income );
            return new DataDto<>( save.getId() );
        } catch (Exception e) {
            return new DataDto<>( new AppErrorDto( HttpStatus.OK, "Bad credentional", "product" ) );

        }
    }

    @Override
    public DataDto<List<InComeProductsDto>> getAll() {
        List<InComeProducts> inComeProductsList = repository.findAll();
        List<InComeProductsDto> inComeProductsDto = mapper.toDto( inComeProductsList );
        return new DataDto<>( inComeProductsDto );
    }

    @Override
    public DataDto<InComeProductsDto> get(Long id) {
        Optional<InComeProducts> optional = repository.findById( id );
        if (optional.isEmpty()) {
            return new DataDto<>( new AppErrorDto( HttpStatus.OK, "Income Product not found", "product" ) );
        }
        InComeProductsDto inComeProductsDto = mapper.toDto( optional.get() );
        return new DataDto<>( inComeProductsDto );
    }

    public DataDto<List<InComeProductsDto>> getByTime(String from, String to) {
        try {
            LocalDate fromDate = LocalDate.parse( from );
            LocalDate toDate = LocalDate.parse( to );
            List<InComeProducts> allByCreatedAt_date = repository.findAllByDateTimeDateBetween( fromDate, toDate );
            return new DataDto<>( mapper.toDto( allByCreatedAt_date ) );
        } catch (DateTimeParseException e) {
            return new DataDto<>( new AppErrorDto( "UnValid Date format", HttpStatus.BAD_REQUEST ) );
        }
    }

    public List<InComeProducts> getByGroupProductsId(Long id) {
        return repository.findAllByGroupProductsId( id );
    }
}
