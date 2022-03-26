package uz.master.warehouse.dto.market;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;

@Getter
@Setter
public class MarketDto extends GenericDto {
    private String name;
    private Long ownerId;
    private Long organizationId;
    private String location;
    private String description;


}
