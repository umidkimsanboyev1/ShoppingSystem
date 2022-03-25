package uz.master.warehouse.mapper.clientBar;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.clientBar.ClientBarCreateDto;
import uz.master.warehouse.dto.clientBar.ClientBarDto;
import uz.master.warehouse.dto.clientBar.ClientBarUpdateDto;
import uz.master.warehouse.entity.clientBar.ClientBar;
import uz.master.warehouse.mapper.BaseMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ClientBarMapper extends BaseMapper {
    ClientBar fromDto(ClientBarCreateDto createDto);

    ClientBar fromUpdateDto(ClientBarUpdateDto updateDto);

    List<ClientBarDto> toDto(List<ClientBar> clientBars);

    ClientBarDto toDto(ClientBar clientBar);
}
