package uz.master.warehouse.dto.payment;



import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.annotations.HaveCompany;
import uz.master.warehouse.annotations.HaveOrg;
import uz.master.warehouse.dto.BaseDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;


@Getter
@Setter
public class PaymentCreateDto implements BaseDto {

    @NotBlank
    @HaveOrg
    @Positive
    private Long organizationId;

    @NotBlank
    @HaveCompany
    private Long companyId;

    @NotBlank
    private Long sum;
}
