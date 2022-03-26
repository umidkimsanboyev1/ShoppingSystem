package uz.master.warehouse.dto.product;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductCreateDto implements BaseDto {

    @NotBlank
    @Min(2)
    private Integer item_count;

    @NotBlank
    private String model;

    private String color;

    @NotNull
    private Long firmId;

    private Double default_price;

}
