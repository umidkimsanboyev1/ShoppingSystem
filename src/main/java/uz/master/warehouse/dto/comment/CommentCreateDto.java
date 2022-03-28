package uz.master.warehouse.dto.comment;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CommentCreateDto implements BaseDto {
    @NotBlank
    private Long clientBarId;
    @NotBlank
    private String text;
}
