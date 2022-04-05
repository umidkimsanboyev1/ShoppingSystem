package uz.master.warehouse.dto.sector;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;

@Getter
@Setter
public class SectorUpdateDto extends GenericDto {
    private String name;

    private String color;

    private Long wareHouseId;
}
