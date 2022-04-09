package uz.master.warehouse.dto.wareHouse;

import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;

import javax.persistence.Column;
import java.util.Date;

public class WareHouseCreateDto implements BaseDto {

    private String name;

    private String location;

    private Date paidDate;// yoqotish

    private Long organizationId;
}
