package uz.master.warehouse.dto.market;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MarketUpdateDto extends GenericDto {

    @NotBlank
    private String name;
    private String location;
    private String description;

}
