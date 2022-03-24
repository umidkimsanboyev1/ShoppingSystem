package uz.master.warehouse.dto.organization;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;

@Getter
@Setter
public class OrganizationCreateDto implements BaseDto {
    private Long OwnerId;
    private String name;
}
