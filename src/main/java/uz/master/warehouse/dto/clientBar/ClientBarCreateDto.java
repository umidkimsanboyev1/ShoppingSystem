package uz.master.warehouse.dto.clientBar;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;
import uz.master.warehouse.entity.Product;

import java.util.List;


@Getter
@Setter
public class ClientBarCreateDto extends GenericDto {
    private List<Product> products;
    private String clientName;
}
