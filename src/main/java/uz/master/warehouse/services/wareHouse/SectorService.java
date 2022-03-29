package uz.master.warehouse.services.wareHouse;

import org.springframework.stereotype.Service;
import uz.master.warehouse.mapper.wareHouse.SectorMapper;
import uz.master.warehouse.repository.wareHouse.SectorRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.validator.warehouse.SectorValidator;

@Service
public class SectorService extends AbstractService<SectorRepository, SectorMapper, SectorValidator> {

    public SectorService(SectorRepository repository, SectorMapper mapper, SectorValidator validator) {
        super(repository, mapper, validator);
    }
}
