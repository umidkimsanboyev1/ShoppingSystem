package uz.master.warehouse.dto.company;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;

@Getter
@Setter
public class CompanyCreateDto implements BaseDto {
    private String name;
    private Long registerNumber;
}
