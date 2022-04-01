package uz.master.warehouse.dto.comment;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;
@Getter
@Setter
public class CommentDto extends GenericDto {
    private Long clientBarId;
    private Long authorId;
    private String text;
}
