package uz.master.warehouse.dto.organization;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;

@Getter
@Setter
public class OrganizationUpdateDto extends GenericDto {
    private Long id;
    private String name;
}
