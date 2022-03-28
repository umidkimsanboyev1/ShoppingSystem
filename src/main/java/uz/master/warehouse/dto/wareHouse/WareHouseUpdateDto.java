package uz.master.warehouse.dto.wareHouse;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class WareHouseUpdateDto extends GenericDto {
    @NotBlank
    private String name;

    @NotBlank
    private String location;
}
