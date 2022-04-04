package uz.master.warehouse.dto.organization;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OrganizationUpdateDto extends GenericDto {
    @NotBlank
    private String name;
}
