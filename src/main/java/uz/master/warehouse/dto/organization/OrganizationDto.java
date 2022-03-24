package uz.master.warehouse.dto.organization;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;

@Getter
@Setter
public class OrganizationDto extends GenericDto {
    private String name;
    private Long ownerId;
}
