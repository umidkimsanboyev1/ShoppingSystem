package uz.master.warehouse.dto.product;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class ProductUpdateDto extends GenericDto {

    @NotNull
    private Double default_price;

}
