package uz.master.warehouse.dto.clientBar;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;
import uz.master.warehouse.dto.product.ProductCreateDto;

import java.util.List;


@Getter
@Setter
public class ClientBarCreateDto implements BaseDto {
    private List<ProductCreateDto> products;
    private String clientName;
}
