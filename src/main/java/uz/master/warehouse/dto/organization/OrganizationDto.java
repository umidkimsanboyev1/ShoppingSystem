package uz.master.warehouse.dto.organization;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OrganizationDto extends GenericDto {
    @NotBlank
    private Long ownerId;
    @NotBlank
    private String name;
}
