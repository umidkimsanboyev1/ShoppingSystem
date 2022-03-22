package uz.master.warehouse.dto.organization;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.BaseDto;

@Getter
@Setter
public class OrganizationDto implements BaseDto {
    private String name;
    private Long ownerId;
}
