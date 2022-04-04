package uz.master.warehouse.dto.organization;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrganizationCreateDto implements BaseDto {
    @NotBlank
    private Long OwnerId;
    @NotBlank
    private String name;
}
