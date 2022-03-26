package uz.master.warehouse.dto.market;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;

@Getter
@Setter
public class MarketUpdateDto extends GenericDto {

    private String name;
    private String location;
    private String description;

}
