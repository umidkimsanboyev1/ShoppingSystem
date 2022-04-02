package uz.master.warehouse.dto.outComeProducts;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OutComeProductsDto extends GenericDto {
    private Double productPrice;

    private int count;

    private Long productId;

    private Long clientBarId;

    private boolean taken;


}
