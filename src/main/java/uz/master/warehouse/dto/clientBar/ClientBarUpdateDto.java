package uz.master.warehouse.dto.clientBar;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;
import uz.master.warehouse.entity.Product;

import java.util.List;

@Getter
@Setter
public class ClientBarUpdateDto extends GenericDto {
    private List<Product> products;
    private String clientName;
    private Double overAllPrice;
    private Integer productCount;
    private boolean paid;
    private boolean taken;
}
