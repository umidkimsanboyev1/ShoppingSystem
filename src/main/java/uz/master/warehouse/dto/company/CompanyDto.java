package uz.master.warehouse.dto.company;

import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;

import java.util.List;

public class CompanyDto extends GenericDto implements BaseDto {
    private String name;
    private Long registrationNumber;
    private List<Item> items;
}
