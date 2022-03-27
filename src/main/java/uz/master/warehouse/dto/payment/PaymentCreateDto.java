package uz.master.warehouse.dto.payment;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.BaseDto;


@Getter
@Setter
public class PaymentCreateDto implements BaseDto {

    private Long organizationId;
    private Long companyId;
    private Long sum;
}
