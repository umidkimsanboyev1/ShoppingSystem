package uz.master.warehouse.services.product;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.groupProducts.GroupProductsCreateDto;
import uz.master.warehouse.dto.groupProducts.GroupProductsDto;
import uz.master.warehouse.dto.groupProducts.GroupProductsUpdateDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.product.GroupProducts;
import uz.master.warehouse.entity.products.InComeProducts;
import uz.master.warehouse.mapper.groupProducts.GroupProductsMapper;
import uz.master.warehouse.repository.product.GroupProductsRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;
import uz.master.warehouse.services.products.InComeProductsService;
import uz.master.warehouse.services.products.WareHouseProductsService;

import javax.validation.Valid;
import java.util.List;

@Service
public  class GroupProductsService extends AbstractService<GroupProductsRepository, GroupProductsMapper>
        implements GenericCrudService<
        GroupProducts,
        GroupProductsDto,
        GroupProductsCreateDto,
        GroupProductsUpdateDto,
        Long> {

    private final InComeProductsService inComeProductsService;
    private final WareHouseProductsService wareHouseProductsService;

    public GroupProductsService(GroupProductsRepository repository,
                                GroupProductsMapper mapper, InComeProductsService inComeProductsService, WareHouseProductsService wareHouseProductsService) {
        super(repository, mapper);
        this.inComeProductsService = inComeProductsService;
        this.wareHouseProductsService = wareHouseProductsService;
    }

    @Override
    public DataDto<Long> create(@Valid GroupProductsCreateDto createDto) {

        GroupProducts groupProducts = mapper.fromCreateDto(createDto);
        groupProducts.setCompanyId(createDto.getCompanyId());
        groupProducts.setDate(createDto.getDate());
        GroupProducts save = repository.save(groupProducts);

        List<InComeProducts> byGroupProductsId = inComeProductsService.getByGroupProductsId(save.getId());
        wareHouseProductsService.incomeProducts(byGroupProductsId);

        return new DataDto<>(save.getId());
    }

    @Override
    public DataDto<Void> delete(Long id) {
        repository.deleteGroupProducts(id);
        return new DataDto<>();
    }

    @Override
    public DataDto<Long> update(GroupProductsUpdateDto updateDto) {

        GroupProducts groupProducts = mapper.fromUpdateDto(updateDto);
        groupProducts.setCompanyId(updateDto.getCompanyId());
        groupProducts.setDate(updateDto.getDate());
        GroupProducts save = repository.save(groupProducts);
        return new DataDto<>(save.getId());

    }

    @Override
    public DataDto<List<GroupProductsDto>> getAll() {
        List<GroupProducts> list = repository.findAll();
        return new DataDto<>(mapper.toDto(list));
    }

    @Override
    public DataDto<GroupProductsDto> get(Long id) {
        GroupProducts groupProducts = repository.findById(id).orElseThrow(() -> {
            throw new UsernameNotFoundException("Not found");
        });
        return new DataDto<>(mapper.toDto(groupProducts));
    }
}
