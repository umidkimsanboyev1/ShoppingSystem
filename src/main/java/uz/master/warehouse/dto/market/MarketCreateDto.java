package uz.master.warehouse.dto.market;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.annotations.HaveCompany;
import uz.master.warehouse.annotations.HaveMarket;
import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class MarketCreateDto implements BaseDto {


    @NotBlank
    private String name;


    @NotBlank
    private Long ownerId;


    private String location;
    private String description;

}
