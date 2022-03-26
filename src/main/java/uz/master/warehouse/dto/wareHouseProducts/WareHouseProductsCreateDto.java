package uz.master.warehouse.dto.wareHouseProducts;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.BaseDto;

@Getter
@Setter
public class WareHouseProductsCreateDto implements BaseDto {

    private int count;

    private Long productId;

}
