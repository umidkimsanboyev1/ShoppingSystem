package uz.master.warehouse.dto.company;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CompanyCreateDto implements BaseDto {
    @NotBlank
    private String name;
    @NotBlank
    private Long registerNumber;
}
