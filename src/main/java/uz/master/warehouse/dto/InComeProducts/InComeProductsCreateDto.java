package uz.master.warehouse.dto.InComeProducts;


import lombok.Getter;
import lombok.Setter;
import org.jboss.logging.annotations.Pos;
import uz.master.warehouse.annotations.HaveGroupProducts;
import uz.master.warehouse.annotations.HaveProduct;
import uz.master.warehouse.dto.BaseDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class InComeProductsCreateDto implements BaseDto {

    @Positive
    @NotBlank
    private int count;

    @NotBlank
    @HaveProduct
    private Long productId;

    @NotBlank
    @Positive
    private Double itemPrice;

    @HaveGroupProducts(message = "GroupProducts Not Found!")
    private Long groupProductsId;

}
