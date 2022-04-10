package uz.master.warehouse.dto.firm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uz.master.warehouse.annotations.HaveCompany;
import uz.master.warehouse.dto.GenericDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class FirmDto extends GenericDto {
    private String name;

    @HaveCompany
    @Positive
    @NotBlank
    private Long companyId;
}
