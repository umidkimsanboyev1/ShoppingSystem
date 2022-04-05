package uz.master.warehouse.dto.firm;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.annotations.HaveCompany;
import uz.master.warehouse.dto.BaseDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class FirmCreateDto implements BaseDto {

    @HaveCompany
    @Positive
    @NotBlank
    private Long companyId;
}
