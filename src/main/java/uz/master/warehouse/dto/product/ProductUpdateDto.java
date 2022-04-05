package uz.master.warehouse.dto.product;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Getter
@Setter
public class ProductUpdateDto extends GenericDto {

    @NotNull
    @Positive
    private Double default_price;

}
