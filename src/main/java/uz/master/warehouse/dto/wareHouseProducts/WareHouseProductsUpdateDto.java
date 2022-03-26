package uz.master.warehouse.dto.wareHouseProducts;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;

@Getter
@Setter
public class WareHouseProductsUpdateDto extends GenericDto {

    private int count;

    private Long productId;

}
