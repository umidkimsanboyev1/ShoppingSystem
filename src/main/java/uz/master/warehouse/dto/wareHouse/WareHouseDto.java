package uz.master.warehouse.dto.wareHouse;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;

import java.util.Date;

@Getter
@Setter
public class WareHouseDto extends GenericDto {
    private Long marketId;

    private String name;


    private String location;

    private Date paidDate;

    private Long organizationId;
}
