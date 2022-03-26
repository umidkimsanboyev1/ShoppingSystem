package uz.master.warehouse.dto.product;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductCreateDto extends GenericDto {

    @NotNull
    private Integer item_count;

    @NotNull
    private String model;

    private String color;

    @NotNull
    private Long firmId;

    private Double default_price;

}
