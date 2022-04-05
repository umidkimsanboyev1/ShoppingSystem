package uz.master.warehouse.dto.sector;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.BaseDto;

@Getter
@Setter
public class SectorCreateDto implements BaseDto {
    private String name;

    private String color;

    private Long wareHouseId;
}
