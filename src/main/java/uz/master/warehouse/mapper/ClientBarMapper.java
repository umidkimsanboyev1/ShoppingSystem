package uz.master.warehouse.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.clientBar.ClientBarCreateDto;
import uz.master.warehouse.entity.ClientBar;

@Component
@Mapper(componentModel = "spring")
public interface ClientBarMapper extends BaseMapper {
    ClientBar fromDto(ClientBarCreateDto createDto);
}
