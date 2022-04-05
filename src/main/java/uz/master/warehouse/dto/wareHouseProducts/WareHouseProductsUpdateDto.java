package uz.master.warehouse.dto.wareHouseProducts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jboss.logging.annotations.Pos;
import uz.master.warehouse.annotations.HaveProduct;
import uz.master.warehouse.dto.GenericDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WareHouseProductsUpdateDto extends GenericDto {

    @Positive
    @NotBlank
    private int count;

    @HaveProduct
    @NotBlank
    private Long productId;

}
