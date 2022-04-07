package uz.master.warehouse.dto.payment;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.annotations.HaveCompany;
import uz.master.warehouse.annotations.HaveOrg;
import uz.master.warehouse.dto.GenericDto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentDto extends GenericDto {

    @NotBlank
    @HaveOrg
    private Long organizationId;

    @NotBlank
    @HaveCompany
    private Long companyId;

    @NotBlank
    private Long sum;
    private LocalDateTime dateTime;

}
