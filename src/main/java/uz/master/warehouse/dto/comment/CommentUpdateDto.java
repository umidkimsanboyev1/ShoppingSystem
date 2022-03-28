package uz.master.warehouse.dto.comment;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.GenericDto;

@Getter
@Setter
public class CommentUpdateDto extends GenericDto {
    private String text;
}
