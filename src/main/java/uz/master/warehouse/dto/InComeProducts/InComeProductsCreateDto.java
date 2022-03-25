package uz.master.warehouse.dto.InComeProducts;


import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.BaseDto;

@Getter
@Setter
public class InComeProductsCreateDto implements BaseDto {

    private int count;

    private Long productId;

    private Double itemPrice;

    private Long groupProductsId;

}
