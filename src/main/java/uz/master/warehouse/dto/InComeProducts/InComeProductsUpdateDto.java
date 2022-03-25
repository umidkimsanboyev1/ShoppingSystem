package uz.master.warehouse.dto.InComeProducts;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;


@Getter
@Setter
public class InComeProductsUpdateDto extends GenericDto {

    private int count;

    private Long productId;

    private Double itemPrice;

    private Long groupProductsId;

}
