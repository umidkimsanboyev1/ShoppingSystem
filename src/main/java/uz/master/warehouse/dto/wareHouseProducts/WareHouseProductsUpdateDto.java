package uz.master.warehouse.dto.wareHouseProducts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WareHouseProductsUpdateDto extends GenericDto {

    private int count;

    private Long productId;

}
