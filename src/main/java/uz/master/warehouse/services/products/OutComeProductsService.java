package uz.master.warehouse.services.products;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.outComeProducts.OutComeProductsCreateDto;
import uz.master.warehouse.dto.outComeProducts.OutComeProductsDto;
import uz.master.warehouse.dto.outComeProducts.OutComeProductsUpdateDto;
import uz.master.warehouse.dto.responce.AppErrorDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.products.OutComeProducts;
import uz.master.warehouse.mapper.products.OutComeProductsMapper;
import uz.master.warehouse.repository.products.OutComeProductsRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;
import uz.master.warehouse.services.organization.CompanyService;
import uz.master.warehouse.validator.products.OutComeProductsValidator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class OutComeProductsService extends AbstractService<OutComeProductsRepository, OutComeProductsMapper, OutComeProductsValidator>
        implements GenericCrudService<OutComeProducts, OutComeProductsDto, OutComeProductsCreateDto, OutComeProductsUpdateDto, Long> {

    private final WareHouseProductsService service;
    private final CompanyService companyService;

    public OutComeProductsService(OutComeProductsRepository repository, OutComeProductsMapper mapper, OutComeProductsValidator validator, WareHouseProductsService service, CompanyService companyService) {
        super(repository, mapper, validator);
        this.service = service;
        this.companyService = companyService;
    }


    @Override
    public DataDto<Long> create(OutComeProductsCreateDto createDto) {
        OutComeProducts outComeProducts = mapper.fromCreateDto(createDto);
        service.checkCount(outComeProducts.getProductId(), createDto.getCount());
        return new DataDto<>(repository.saveOutCome(createDto.getClientBarId(), createDto.getCount(), createDto.getProductId(), createDto.getProductPrice()));
    }

    @Override
    public DataDto<Void> delete(Long clientId) {
        repository.deleteAllByClientBarId(clientId);
        return new DataDto<>();
    }

    @Override
    public DataDto<Long> update(OutComeProductsUpdateDto updateDto) {
        OutComeProducts products = repository.findById(updateDto.getId()).orElseThrow();
        mapper.updateModel(updateDto, products);
        return new DataDto<>(repository.save(products).getId());
    }

    @Override
    public DataDto<List<OutComeProductsDto>> getAll() {
        return null;
    }

    public DataDto<List<OutComeProductsDto>> getAll(Long clientId) {
        List<OutComeProducts> products = repository.findAllByClientBarId(clientId);
        List<OutComeProductsDto> dtos = mapper.toDto(products);
        return new DataDto<>(dtos);
    }


    @Override
    public DataDto<OutComeProductsDto> get(Long id) {
        return new DataDto<>(mapper.toDto(repository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Not found");
        })));
    }


    public DataDto<Boolean> updateSale(Long id) {
        OutComeProducts found = repository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("not Found");
        });
        found.setTaken(true);
        repository.save(found);
        return new DataDto<>(Boolean.TRUE);
    }

    public DataDto<List<OutComeProductsDto>> getByDate(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            List<OutComeProducts> allByCreatedAt_date = repository.findAllByCreatedAt_Date(localDate);
            return new DataDto<>(mapper.toDto(allByCreatedAt_date));
        } catch (DateTimeParseException e) {
            return new DataDto<>(new AppErrorDto("UnValid Date format", HttpStatus.BAD_REQUEST));
        }

    }
}
