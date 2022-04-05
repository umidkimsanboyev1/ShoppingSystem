package uz.master.warehouse.dto.payment;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PaymentUpdateDto extends GenericDto {
    @NotBlank
    private Long sum;
}
