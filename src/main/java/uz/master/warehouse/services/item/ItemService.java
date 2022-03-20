package uz.master.warehouse.services.item;


import org.springframework.stereotype.Service;
import uz.master.warehouse.validator.ItemValidator;
import uz.master.warehouse.dto.item.ItemCreateDto;
import uz.master.warehouse.dto.item.ItemDto;
import uz.master.warehouse.dto.item.ItemUpdateDto;
import uz.master.warehouse.entity.Item;
import uz.master.warehouse.mapper.ItemMapper;
import uz.master.warehouse.repository.ItemRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;

import java.util.List;

@Service
public class ItemService extends AbstractService<
        ItemRepository,
        ItemMapper,
        ItemValidator
        > implements GenericCrudService<
        Item,
        ItemDto,
        ItemCreateDto,
        ItemUpdateDto,
        Long
        > {

    public ItemService(ItemRepository repository, ItemMapper mapper, ItemValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public Long create(ItemCreateDto createDto) {
        return null;
    }

    @Override
    public Void delete(Long id) {
        return null;
    }

    @Override
    public Void update(ItemUpdateDto updateDto) {
        return null;
    }

    @Override
    public List<ItemDto> getAll() {
        return null;
    }

    @Override
    public ItemDto get(Long id) {
        return null;
    }
}
