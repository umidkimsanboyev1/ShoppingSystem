package uz.master.warehouse.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public abstract class GenericDto implements BaseDto {
    @NotBlank
    private Long id;
}
