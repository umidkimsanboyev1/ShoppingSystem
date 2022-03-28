package uz.master.warehouse.validator.warehouse;

import org.springframework.stereotype.Component;
import uz.master.warehouse.repository.wareHouse.WareHouseRepository;
import uz.master.warehouse.validator.BaseValidator;

@Component
public class WareHouseValidator implements BaseValidator {
    private final WareHouseRepository repository;

    public WareHouseValidator(WareHouseRepository repository) {
        this.repository = repository;
    }

    public void check(Long id) {
        // TODO: 3/28/2022 organizatsiya bo'yicha check 
        repository.findById(id).orElseThrow(()->{throw new RuntimeException("not found");});
    }
}
