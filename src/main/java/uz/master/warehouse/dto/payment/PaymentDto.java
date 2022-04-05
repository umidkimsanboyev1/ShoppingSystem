package uz.master.warehouse.dto.payment;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;

import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentDto extends GenericDto {
    private Long organizationId;
    private Long companyId;
    private LocalDateTime dateTime;
    private Long sum;
}
