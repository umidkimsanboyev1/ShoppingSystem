package uz.master.warehouse.dto.firm;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;

import javax.validation.constraints.Size;

@Getter
@Setter
public class FirmUpdateDto extends GenericDto {

    @Size(min = 4, max = 12, message = "Name should be must between 4 and 12")
    private String name;
}
