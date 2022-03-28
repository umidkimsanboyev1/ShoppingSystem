package uz.master.warehouse.dto.clientBar;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;
import uz.master.warehouse.dto.InComeProducts.InComeProductsCreateDto;
import uz.master.warehouse.dto.outComeProducts.OutComeProductsCreateDto;
import uz.master.warehouse.dto.product.ProductCreateDto;

import java.util.List;


@Getter
@Setter
public class ClientBarCreateDto implements BaseDto {
    private String clientName;
}
