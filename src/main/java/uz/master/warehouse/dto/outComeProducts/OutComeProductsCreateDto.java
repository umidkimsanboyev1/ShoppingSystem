package uz.master.warehouse.dto.outComeProducts;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import uz.master.warehouse.dto.GenericDto;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OutComeProductsCreateDto extends GenericDto {
    @NotBlank
    private int count;

    @NotBlank
    private Long productId;

    @NotBlank
    private Long clientBarId;

    @NotBlank
    private Double productPrice;
}
