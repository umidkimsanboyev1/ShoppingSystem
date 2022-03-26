package uz.master.warehouse.dto.clientBar;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;
import uz.master.warehouse.entity.product.Product;

import java.util.List;

@Getter
@Setter
public class ClientBarUpdateDto extends GenericDto {
    private String clientName;
    private Double overAllPrice;
    private boolean paid;
}
