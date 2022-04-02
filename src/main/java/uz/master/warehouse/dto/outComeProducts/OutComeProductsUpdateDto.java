package uz.master.warehouse.dto.outComeProducts;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OutComeProductsUpdateDto extends GenericDto {


    private Double productPrice;

    private int count;



}
