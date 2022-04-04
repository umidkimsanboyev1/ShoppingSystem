package uz.master.warehouse.dto.company;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CompanyUpdateDto extends GenericDto {
    @NotBlank
    private String name;
}
