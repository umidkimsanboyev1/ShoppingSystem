package uz.master.warehouse.dto.firm;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.annotations.HaveCompany;
import uz.master.warehouse.dto.BaseDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
public class FirmCreateDto implements BaseDto {

    @Size(min = 4, max = 12, message = "Name should be between 4 and 12")
    private String name;

    @HaveCompany
    @Positive
    @NotBlank
    private Long companyId;
}
