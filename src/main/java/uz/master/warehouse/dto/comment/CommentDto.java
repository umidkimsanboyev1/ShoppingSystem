package uz.master.warehouse.dto.comment;

import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;

public class CommentDto extends GenericDto {
    private Long clientBarId;
    private Long authorId;
    private String text;
}
