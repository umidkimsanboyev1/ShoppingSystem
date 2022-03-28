package uz.master.warehouse.dto.wareHouseProducts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.master.warehouse.dto.BaseDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WareHouseProductsCreateDto implements BaseDto {

    private int count;

    private Long productId;

}
