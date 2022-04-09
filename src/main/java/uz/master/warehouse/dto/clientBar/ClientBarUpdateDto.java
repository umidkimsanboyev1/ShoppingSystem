package uz.master.warehouse.dto.clientBar;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;
import uz.master.warehouse.entity.product.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class ClientBarUpdateDto extends GenericDto {

    @NotBlank
    @Size(min = 4, max = 12, message = "Client name should be between 4 and 12")
    private String clientName;

    @NotNull
    @Positive
    private Long orgId;

    @Positive
    private Double overAllPrice;

    private boolean paid;

    private boolean taken;
}
