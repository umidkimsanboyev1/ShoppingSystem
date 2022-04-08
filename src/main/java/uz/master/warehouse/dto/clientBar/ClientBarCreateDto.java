package uz.master.warehouse.dto.clientBar;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.annotations.HaveMarket;
import uz.master.warehouse.annotations.HaveOrg;
import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;
import uz.master.warehouse.dto.InComeProducts.InComeProductsCreateDto;
import uz.master.warehouse.dto.outComeProducts.OutComeProductsCreateDto;
import uz.master.warehouse.dto.product.ProductCreateDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
public class ClientBarCreateDto implements BaseDto {

    @NotBlank
    @Size(min = 4, max = 12, message = "Client name should be between 4 and 12")
    private String clientName;

    @HaveOrg
    private Long orgId;

    @HaveMarket
    private Long marketId;
}
