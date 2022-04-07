package uz.master.warehouse.dto.company;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;

@Getter
@Setter
public class CompanyDto extends GenericDto {
    private String name;
    private Long registrationNumber;
}
