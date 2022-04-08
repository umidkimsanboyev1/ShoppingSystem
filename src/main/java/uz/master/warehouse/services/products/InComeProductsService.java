package uz.master.warehouse.services.products;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.master.warehouse.criteria.GenericCriteria;
import uz.master.warehouse.dto.InComeProducts.InComeProductsCreateDto;
import uz.master.warehouse.dto.InComeProducts.InComeProductsDto;
import uz.master.warehouse.dto.InComeProducts.InComeProductsUpdateDto;
import uz.master.warehouse.dto.firm.FirmDto;
import uz.master.warehouse.dto.responce.AppErrorDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.products.InComeProducts;
import uz.master.warehouse.mapper.products.InComeProductsMapper;
import uz.master.warehouse.repository.products.InComeProductsRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
public class InComeProductsService extends AbstractService<InComeProductsRepository, InComeProductsMapper>
        implements GenericCrudService<InComeProducts, InComeProductsDto, InComeProductsCreateDto, InComeProductsUpdateDto, GenericCriteria, Long> {

    public InComeProductsService(InComeProductsRepository repository, InComeProductsMapper mapper,  WareHouseProductsService wareHouseProductsService) {
        super(repository, mapper);
        this.wareHouseProductsService = wareHouseProductsService;
    }

    private final WareHouseProductsService wareHouseProductsService;

    @Override
    public DataDto<Long> create(InComeProductsCreateDto createDto) {
        InComeProducts inComeProducts = mapper.fromCreateDto(createDto);
        Long id = repository.save(inComeProducts).getId();
        inComeProducts.setId(id);
        wareHouseProductsService.incomeProducts(inComeProducts);
        return new DataDto<>(id);
    }

    @Override
    public DataDto<Void> delete(Long id) {
        Long income = repository.findbyIncome(id);
        if (income < 1) {
            return new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, "Income  Product not found", "product"));
        }
        repository.deleteIncome(id);
        return new DataDto<>();
    }

    @Override
    public DataDto<Long> update(InComeProductsUpdateDto updateDto) {
        Optional<InComeProducts> optional = repository.findByIncome(updateDto.getId());
        if (optional.isEmpty()) {

            return new DataDto<>(new AppErrorDto(HttpStatus.OK, "Income Product not found", "product"));
        }
        InComeProducts income = mapper.fromUpdateDto(updateDto, optional.get());
        try {

            InComeProducts save = repository.save(income);
            return new DataDto<>(save.getId());
        } catch (Exception e) {
            return new DataDto<>(new AppErrorDto(HttpStatus.OK, "Bad credentional", "product"));

        }
    }

    @Override
    public DataDto<List<InComeProductsDto>> getAll() {
        List<InComeProducts> inComeProductsList = repository.findAll();
        List<InComeProductsDto> inComeProductsDto = mapper.toDto(inComeProductsList);
        return new DataDto<>(inComeProductsDto);
    }

    @Override
    public DataDto<InComeProductsDto> get(Long id) {
        Optional<InComeProducts> optional = repository.findById(id);
        if (optional.isEmpty()) {
            return new DataDto<>(new AppErrorDto(HttpStatus.OK, "Income Product not found", "product"));
        }
        InComeProductsDto inComeProductsDto = mapper.toDto(optional.get());
        return new DataDto<>(inComeProductsDto);
    }

    @Override
    public DataDto<List<InComeProductsDto>> getWithCriteria(GenericCriteria criteria) {
        PageRequest request = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<InComeProducts> inComeProductsList = repository.findAllByDeletedFalse(request);
        List<InComeProductsDto> inComeProductsDtos = mapper.toDto(inComeProductsList);
        return new DataDto<>(inComeProductsDtos);

    }

    public DataDto<List<InComeProductsDto>> getByTime(String from, String to) {
        try {
            LocalDate fromDate = LocalDate.parse(from);
            LocalDate toDate = LocalDate.parse(to);
            List<InComeProducts> allByCreatedAt_date = repository.findAllByDateTimeDateBetween(fromDate, toDate);
            return new DataDto<>(mapper.toDto(allByCreatedAt_date));
        } catch (DateTimeParseException e) {
            return new DataDto<>(new AppErrorDto("UnValid Date format", HttpStatus.BAD_REQUEST));
        }
    }

    public List<InComeProducts> getByGroupProductsId(Long id) {
        return repository.findAllByGroupProductsId(id);
    }

    public InComeProducts getByGroupProducts(Long id) {
        return repository.findByGroupProductsId(id);
    }
}
