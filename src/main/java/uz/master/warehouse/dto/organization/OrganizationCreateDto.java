package uz.master.warehouse.dto.organization;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;

@Getter
@Setter
public class OrganizationCreateDto extends GenericDto {
    private Long OwnerId;
    private String name;
}
